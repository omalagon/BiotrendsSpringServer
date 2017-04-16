package com.biotrends.assemblers.usuario;

import com.biotrends.assemblers.EntityResource;
import com.biotrends.entities.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UsuarioResource", description = "The Usuario Resource Representation")
public class UsuarioResource extends EntityResource {

	private static final long serialVersionUID = -6424853385762133289L;

	@JsonProperty
    private Usuario usuario;

    @JsonProperty
    private String idUsuarioCreador;

    @Builder
    public static UsuarioResource target(Usuario usuario, String idUsuarioCreador){
        UsuarioResource usuarioResource = new UsuarioResource();
        usuarioResource.setUsuario(usuario);
        usuarioResource.setIdUsuarioCreador(idUsuarioCreador);

        return usuarioResource;
    }
}
