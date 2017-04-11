package com.biotrends.services.solicitud.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.entities.solicitud.Solicitud;
import com.biotrends.entities.usuario.Usuario;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.solicitud.SolicitudRepository;
import com.biotrends.services.consumo.ConsumoService;
import com.biotrends.services.item.ItemService;
import com.biotrends.services.itemxsolicitud.ItemxSolicitudService;
import com.biotrends.services.solicitud.SolicitudService;
import com.biotrends.services.usuario.UsuarioService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Oscar Malagon
 * @since 6/02/2017
 */
@Service
@Slf4j
public class DefaultSolicitudService implements SolicitudService{
	
	private final SolicitudRepository repository;
	private final UsuarioService usuarioService;
	private final ItemxSolicitudService ixsService;
	private final ItemService itemService;
	private final ConsumoService consumoService;
	
	@Autowired
	public DefaultSolicitudService(SolicitudRepository repository, 
			UsuarioService usuarioService, 
			ItemxSolicitudService ixsService,
			ItemService itemService,
			ConsumoService consumoService) {
		this.repository = repository;
		this.usuarioService = usuarioService;
		this.ixsService = ixsService;
		this.itemService = itemService;
		this.consumoService = consumoService;
	}
	
	@Override
	public Optional<Solicitud> createOrUpdateSolicitud(Solicitud solicitud) {
		checkNotNull(solicitud.getSolicitante(), "El id del solicitante no puede ser nulo");
		
		Optional<Usuario> solicitante = usuarioService.findById(solicitud.getSolicitante());
		checkArgument(solicitante.isPresent(), "El solicitante ingresado no existe");
		
		if(!isNullOrEmpty(solicitud.getAuxiliarOficina())){
			Optional<Usuario> auxOfi = usuarioService.findById(solicitud.getAuxiliarOficina());
			checkArgument(auxOfi.isPresent(), "El auxiliar de oficina ingresado no existe");			
		}
		
		validarExistenciaDeItems(solicitud);
		
		setIdSolicitud(solicitud);
		
		if(solicitud.getId() != null){
			Optional<Solicitud> solicitudEncontrada = findById(solicitud.getId());
			if(solicitudEncontrada.isPresent() && !solicitudEncontrada.get().getEsRevisado()){
				
				deleteItemsXSolicitud(solicitudEncontrada.get());
				
				Solicitud updatedSolicitud = solicitudEncontrada.get();
				updatedSolicitud.setAuxiliarOficina(solicitud.getAuxiliarOficina());
				updatedSolicitud.setEsRevisado(solicitud.getEsRevisado());
				updatedSolicitud.setFechaSolicitud(solicitud.getFechaSolicitud());
				updatedSolicitud.setObservaciones(solicitud.getObservaciones());
				updatedSolicitud.setSolicitante(solicitud.getSolicitante());
				updatedSolicitud.setItmxsol(solicitud.getItmxsol());
				
				return Optional.ofNullable(repository.saveAndFlush(updatedSolicitud));
			}
			
			log.error("No fue posible actualizar la solicitud");
            throw new EntityNotFoundException();
		}
		
		return Optional.ofNullable(repository.saveAndFlush(solicitud));
	}

	private synchronized void setIdSolicitud(Solicitud solicitud) {
		if(solicitud.getIdSolicitud() == null){
    		Long max = repository.getLastSolicitud();
    		if(max == null){
    			solicitud.setIdSolicitud(1L);
    		}else{
    			solicitud.setIdSolicitud(max + 1L);
    		}
    	}
	}

	/**
	 * @param solicitud
	 */
	private void validarExistenciaDeItems(Solicitud solicitud) {
		if(solicitud.getItmxsol() != null && !solicitud.getItmxsol().isEmpty()){
			for(ItemXSolicitud ixs : solicitud.getItmxsol()){
				if(!itemService.findById(ixs.getIdItem()).isPresent()){
					log.error("El item [" + ixs.getIdItem() + "] no existe");
		            throw new EntityNotFoundException();
				}
			}
		}
	}

	/**
	 * @param solicitud
	 */
	private void deleteItemsXSolicitud(Solicitud solicitud) {
		if(solicitud.getItmxsol() != null && !solicitud.getItmxsol().isEmpty()){
			for(ItemXSolicitud ixs : solicitud.getItmxsol()){
				ixsService.delete(ixs.getId());
			}
		}
	}

	@Override
	public Optional<Solicitud> findById(String id) {
		checkNotNull(id, "El id de la solicitud no puede ser nulo");
		
		try {
            Solicitud solicitud = repository.findOne(id);

            return Optional.ofNullable(solicitud);
        } catch (Exception ex) {
            log.error("Error buscando la solicitud con id [" + id + "]", ex);
            throw new EntityNotFoundException();
        }
	}

	@Override
	public Page<Solicitud> findAll(int page, int size) {
		checkNotNull(page, "La página no puede ser null");
        checkNotNull(size, "El tamaño de la pagina no puede ser null");
        checkArgument(page >= 0, "La página no puede ser negativa");
        checkArgument(size >= 0, "El tamaño de la página no puede ser negativa");

        try {
            Pageable pageable = new PageRequest(page, size);
            return repository.findAll(pageable);
        } catch (Exception ex) {
            log.error("Error buscando las solicitudes", ex);
            throw new EntityNotFoundException();
        }
	}

	@Override
	public List<Solicitud> findAll() {
		try {
            return repository.findAll();
        } catch (Exception ex) {
            log.error("Error buscando las solicitudes", ex);
            throw new EntityNotFoundException();
        }
	}

	@Override
	public Optional<Solicitud> delete(String id) {
		checkNotNull(id, "El id de la solicitud no puede ser nulo");
		
		Optional<Solicitud> solicitud = findById(id);
		
		try{
			if(solicitud.isPresent()){
				repository.delete(solicitud.get());				
			}
			
			return solicitud;

		}catch (Exception ex) {
			log.error("Error eliminando la solicitud con id [" + id + "]", ex);
            throw new CommonBiotrendsRuntimeException("Error eliminando la solicitud con id [" + id + "]", ex);
        
		}
	}

}
