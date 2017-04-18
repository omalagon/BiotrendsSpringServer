package com.biotrends.api.usuario.impl;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biotrends.api.usuario.UsuarioController;
import com.biotrends.assemblers.usuario.UsuarioResource;
import com.biotrends.assemblers.usuario.UsuarioResourceAssembler;
import com.biotrends.entities.usuario.Usuario;
import com.biotrends.services.usuario.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
@RestController
@Api(value = "api-usuario", description = "Usuario MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/usuario", produces = APPLICATION_HAL_JSON_VALUE)
public class DefaultUsuarioControllerImpl implements UsuarioController {

    private final UsuarioService service;
    private final UsuarioResourceAssembler assembler;
    
    @Autowired public DefaultUsuarioControllerImpl(UsuarioService service,
    		UsuarioResourceAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<UsuarioResource>> getUsuarios() {
        List<Usuario> usuarioList = service.findAll();
        List<UsuarioResource> resources = new ArrayList<>();
        for (Usuario usuario : usuarioList) {
			resources.add(assembler.toResource(usuario));
		}
        
        return new ResponseEntity<>(resources, OK);
    }

    @Override @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<UsuarioResource> getUsuarioById(@PathVariable final String id) {
        Optional<Usuario> usuarioById = service.findById(id);
        if (usuarioById.isPresent()) {

            return new ResponseEntity<>(assembler.toResource(usuarioById.get()), OK);
        }

        throw new EntityNotFoundException("Usuario not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<UsuarioResource> deleteUsuarioById(@PathVariable final String id) {
        Optional<Usuario> usuarioById = service.delete(id);
        if (usuarioById.isPresent()) {

            return new ResponseEntity<>(assembler.toResource(usuarioById.get()), GONE);
        }

        throw new EntityNotFoundException("Usuario not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<UsuarioResource> createUsuario(
        @ApiParam(value = "The usuario object", required = true) @RequestBody(required = true)
            UsuarioResource usuario) {

        if (usuario != null) {
        	Usuario usuarioACrear = assembler.fromResource(usuario);
            Optional<Usuario> usuarioGuardado = service.createOrUpdateUsuario(usuarioACrear);
            if (usuarioGuardado.isPresent()) {
                return new ResponseEntity<>(assembler.toResource(usuarioGuardado.get()), OK);
            }

            throw new EntityNotFoundException("Usuario no guardado");
        }
        return null;
    }

	@Override
	@RequestMapping(value = "/login/{id}/{password}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<UsuarioResource> loginUser(@PathVariable final String id, @PathVariable final String password) {
		
		Optional<Usuario> usuario = service.findByIdPassword(id, password);
		if(usuario.isPresent()){
			return new ResponseEntity<>(assembler.toResource(usuario.get()), OK);
		}

		throw new EntityNotFoundException("Credenciales invalidas".concat(id).concat("-->").concat(password));
	}

}
