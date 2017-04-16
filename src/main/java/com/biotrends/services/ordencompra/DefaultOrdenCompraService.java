package com.biotrends.services.ordencompra;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.entities.ordencompra.OrdenCompra;
import com.biotrends.entities.usuario.Usuario;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.ordencompra.OrdenCompraRepository;
import com.biotrends.services.itemxsolicitud.ItemxSolicitudService;
import com.biotrends.services.usuario.UsuarioService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Oscar Malagon
 * @since 14/04/2017
 *
 */
@Slf4j
@Service
public class DefaultOrdenCompraService implements OrdenCompraService {

	private final OrdenCompraRepository repository;
	private final UsuarioService usuarioService;
	private final ItemxSolicitudService itemXSolicitudService;
	
	@Autowired
	public DefaultOrdenCompraService(OrdenCompraRepository repository,
			UsuarioService usuarioService,
			ItemxSolicitudService itemXSolicitudService) {
		this.repository = repository;
		this.usuarioService = usuarioService;
		this.itemXSolicitudService = itemXSolicitudService;
	}
	
	@Override
	public Optional<OrdenCompra> createOrUpdateOrdenCompra(OrdenCompra ordenCompra, List<ItemXSolicitud> itemsSolicitud) {
		checkNotNull(ordenCompra.getUsuario(), "La persona que generó la orden de compra no puede ser nulo");
		Optional<Usuario> auxiliarDeOficina = usuarioService.findById(ordenCompra.getUsuario().getId());
		checkArgument(auxiliarDeOficina.isPresent(), "El auxiliar de oficina ingresado no existe");
		
		setNumeroOrden(ordenCompra);
		List<ItemXSolicitud> itemsAsociados = itemXSolicitudService.getItemsYaAsociados(ordenCompra.getNumeroOrden());
		updateItemsWithNumeroOrden(itemsAsociados, null);
		updateItemsWithNumeroOrden(itemsSolicitud, ordenCompra.getNumeroOrden());
		
		return Optional.ofNullable(repository.saveAndFlush(ordenCompra));
	}

	@Override
	public Optional<OrdenCompra> findByNumeroOrden(Long numeroOrden) {
		checkNotNull(numeroOrden, "El numero de orden no puede ser null");
		try {
			return Optional.ofNullable(repository.getOrdenCompraByNumeroOrden(numeroOrden));
		} catch (Exception ex) {
			log.error("No se encontro orden de compra asociada al numero [" + numeroOrden + "]");
			throw new CommonBiotrendsRuntimeException("No se encontro orden de compra asociada al numero [" + numeroOrden + "]", ex);
		}
	}

	@Override
	public List<OrdenCompra> findAll() {
		try {
			return repository.findAll();
		} catch (Exception ex) {
			log.error("Error buscando las ordenes de compra");
			throw new CommonBiotrendsRuntimeException("Error buscando las ordenes de compra", ex);
		}
	}
	
	private synchronized void setNumeroOrden(OrdenCompra ordenCompra) {
		if(ordenCompra.getNumeroOrden() == null){
    		Long max = repository.getLastOrdenCompra();
    		if(max == null){
    			ordenCompra.setNumeroOrden(1L);
    		}else{
    			ordenCompra.setNumeroOrden(max + 1L);
    		}
    	}
	}
	
	private void updateItemsWithNumeroOrden(List<ItemXSolicitud> items, Long numeroOrden){
		for (ItemXSolicitud item : items) {
			item.setNumOrdenAsociado(numeroOrden);
			itemXSolicitudService.createOrUpdateItemXSolicitud(item);
		}
	}

}
