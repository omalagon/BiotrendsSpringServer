package com.biotrends.services.usuario.impl;

import com.biotrends.entities.usuario.Usuario;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.usuario.UsuarioRepository;
import com.biotrends.services.usuario.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Oscar Malagon
 * @since 29/01/2017.
 */
@Service
@Slf4j
public class DefaultUsuarioService implements UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public DefaultUsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override public Optional<Usuario> createOrUpdateItem(Usuario usuario) {
        checkNotNull(usuario, "El usuario no puede ser nulo");

        if (usuario.getId() != null) {
            Optional<Usuario> usuarioEncontrado = findById(usuario.getId());
            if (usuarioEncontrado.isPresent()) {
                Usuario usuarioToUpdate = usuarioEncontrado.get();
                usuarioToUpdate.setNombre(usuario.getNombre());
                usuarioToUpdate.setCorreo(usuario.getCorreo());
                usuarioToUpdate.setPassword(usuario.getPassword());
                usuarioToUpdate.setLaboratorio(usuario.getLaboratorio());

                return Optional.ofNullable(repository.saveAndFlush(usuarioToUpdate));
            } else {
                return Optional.ofNullable(repository.saveAndFlush(usuario));
            }
        }
        log.error("Error creando o actualizando el usuario");
        throw new CommonBiotrendsRuntimeException("Error creando o actualizando el usuario");
    }

    @Override
    public Optional<Usuario> findById(String id) {
        checkNotNull(id, "El id del usuario no puede ser nulo");

        try {
            Usuario usuario = repository.findOne(id);

            return Optional.ofNullable(usuario);
        } catch (Exception ex) {
            log.error("Error buscando el usuario con id [" + id + "]", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            log.error("Error buscando los usuarios", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Optional<Usuario> delete(String id) {
        Optional<Usuario> usuarioById = findById(id);
        try {
            if(usuarioById.isPresent()){
                if(!usuarioTieneAsociaciones(usuarioById.get())){
                    repository.delete(usuarioById.get());

                    return usuarioById;
                }else{
                    log.error("Error: El usuario con [" + id + "] tiene asociaciones");
                    throw new CommonBiotrendsRuntimeException("Error: El usuario con  [" + id + "] tiene asociaciones");
                }
            }
            log.error("Error buscando item con id [" + id + "]");
            throw new CommonBiotrendsRuntimeException("Error buscando item con id [" + id + "]");
        } catch (Exception ex) {
            log.error("Error eliminando el item con id [" + id + "]", ex);
            throw new CommonBiotrendsRuntimeException("Error eliminando el item con id [" + id + "]", ex);
        }
    }

    private boolean usuarioTieneAsociaciones(Usuario usuario) {

        return usuario.getConsumos() == null &&
            usuario.getOrdenesDeCompra() == null &&
            usuario.getRecepciones() == null &&
            usuario.getSolicitudesPedidas() == null &&
            usuario.getSolicitudesAprobadas() == null ;
    }
}
