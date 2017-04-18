package com.biotrends.assemblers.usuario;

import com.biotrends.assemblers.EntityResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UsuarioResource", description = "The Usuario Resource Representation")
public class UsuarioResource extends EntityResource {

	private static final long serialVersionUID = -6424853385762133289L;
	
	@JsonProperty
	private String nombre;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private String correo;
	
	@JsonProperty
	private String laboratorio;
	
	@JsonProperty
	private String usuarioCreador;

    @Builder
    public static UsuarioResource target(String id,
			String nombre,
			String password,
			String correo,
			String laboratorio,
			String usuarioCreador){
    	UsuarioResource usuario = new UsuarioResource();
    	usuario.setIdentifier(id);
		usuario.setNombre(nombre);
		usuario.setPassword(password);
		usuario.setCorreo(correo);
		usuario.setLaboratorio(laboratorio);
		usuario.setUsuarioCreador(usuarioCreador);
		
		return usuario;
    }
}
