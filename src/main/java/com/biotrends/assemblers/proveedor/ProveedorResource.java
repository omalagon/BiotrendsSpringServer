package com.biotrends.assemblers.proveedor;

import com.biotrends.assemblers.EntityResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @author Oscar Malagon
 * @since 14/04/2017
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProveedorResource", description = "The proveedor Resource Representation")
public class ProveedorResource extends EntityResource {
	
	private static final long serialVersionUID = 6902022705438063921L;
	
	@JsonProperty
	private String nombre;
	
	@JsonProperty
	private String direccion;
	
	@JsonProperty
	private String correo;
	
	@JsonProperty
	private String telefono;
	
	@JsonProperty
	private String fax;
	
	@JsonProperty
	private String celular;
	
	@JsonProperty
	private String contacto;
	
	@JsonProperty
	private String ciudad;
	
	//No son necesarios los itemsxproveedor ni las evaluaciones
	
	@Builder
	public static ProveedorResource target(String id, String nombre, String direccion, String correo,
			String telefono, String fax,
	        String celular, String contacto, String ciudad){
		
		ProveedorResource proveedor= new ProveedorResource();
		proveedor.setIdentifier(id);
		proveedor.setNombre(nombre);
		proveedor.setDireccion(direccion);
		proveedor.setTelefono(telefono);
		proveedor.setFax(fax);
		proveedor.setCelular(celular);
		proveedor.setContacto(contacto);
		proveedor.setCiudad(ciudad);
		proveedor.setCorreo(correo);
		
		return proveedor;
	}

}
