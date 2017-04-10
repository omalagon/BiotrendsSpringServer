package com.biotrends.api.usuario;

import com.biotrends.entities.usuario.Usuario;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Oscar Malagon
 * @since 29/01/2017.
 */
public interface UsuarioController {

    @ApiModelProperty(position = 1, required = true, value = "List of usuarios")
    @ApiOperation(value = "Get usuarios", notes = "Find all usuarios", response = Usuario.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "All usuarios"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Usuario Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Usuario>> getUsuarios();

    @ApiModelProperty(position = 2, required = true, value = "Usuario given its id")
    @ApiOperation(value = "Get usuario given an id", notes = "Find the usuario that match the given id", response = Usuario.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Usuario from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Usuario Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable final String id);

    @ApiModelProperty(position = 3, required = true, value = "Deletes an usuario given its id")
    @ApiOperation(value = "Delete usuario", notes = "Delete usuario that match the given id", response = Usuario.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 410, message = "The usuario has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Usuario Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Usuario> deleteUsuarioById(@PathVariable final String id);

    @ApiModelProperty(position = 4, required = true, value = "Creates an usuario")
    @ApiOperation(value = "Create usuario", notes = "Creates an usuario given its information", response = Usuario.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The usuario has been created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Usuario Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Usuario> createUsuario(
        @ApiParam(value = "The usuario object", required = true) @RequestBody(required = true)
            Usuario usuario);
    
    @ApiModelProperty(position = 5, required = true, value = "Login by id and password")
    @ApiOperation(value = "Login by username and password", notes = "Login by id and password", response = Usuario.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "Login by username and password"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Usuario Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/login/{id}/{password}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Usuario> loginUser(
    		@PathVariable final String id, @PathVariable final String password);
}
