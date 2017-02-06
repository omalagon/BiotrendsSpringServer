package com.biotrends.api.usuario.impl;

import com.biotrends.api.usuario.UsuarioController;
import com.biotrends.assemblers.usuario.UsuarioAssembler;
import com.biotrends.assemblers.usuario.UsuarioResource;
import com.biotrends.entities.usuario.Usuario;
import com.biotrends.services.usuario.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
@RestController
@Api(value = "api-usuario", description = "Usuario MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/usuario", produces = APPLICATION_HAL_JSON_VALUE)
public class DefaultUsuarioControllerImpl implements UsuarioController {

    private final UsuarioService service;
    private final UsuarioAssembler usuarioAssembler;

    @Autowired public DefaultUsuarioControllerImpl(UsuarioService service, UsuarioAssembler usuarioAssembler) {
        this.service = service;
        this.usuarioAssembler = usuarioAssembler;
    }

    @Override @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarioList = service.findAll();

        return new ResponseEntity<>(usuarioList, OK);
    }

    @Override @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Usuario> getItemById(@PathVariable final String id) {
        Optional<Usuario> usuarioById = service.findById(id);
        if (usuarioById.isPresent()) {

            return new ResponseEntity<>(usuarioById.get(), OK);
        }

        throw new EntityNotFoundException("Usuario not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Usuario> deleteItemById(@PathVariable final String id) {
        Optional<Usuario> usuarioById = service.delete(id);
        if (usuarioById.isPresent()) {

            return new ResponseEntity<>(usuarioById.get(), GONE);
        }

        throw new EntityNotFoundException("Usuario not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Usuario> createItem(
        @ApiParam(value = "The usuario object", required = true) @RequestBody(required = true)
            Usuario usuario) {

        if (usuario != null) {

            Optional<Usuario> usuarioGuardado = service.createOrUpdateItem(usuario);
            if (usuarioGuardado.isPresent()) {
                return new ResponseEntity<Usuario>(usuarioGuardado.get(), OK);
            }

            throw new EntityNotFoundException("Usuario no guardado");
        }
        return null;
    }

}
