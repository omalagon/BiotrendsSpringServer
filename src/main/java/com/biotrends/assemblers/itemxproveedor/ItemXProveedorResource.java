package com.biotrends.assemblers.itemxproveedor;

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
@ApiModel(value = "ItemXProveedorResource", description = "The itemxproveedor Resource Representation")
public class ItemXProveedorResource extends EntityResource{

	private static final long serialVersionUID = -8463766881322569290L;
	
	@JsonProperty
	private String item;
	
	@JsonProperty
	private String proveedor;
	
	@JsonProperty
	private Double precio;
	
	@Builder
	public static ItemXProveedorResource target(String item, 
			String proveedor, 
			Double precio){
		ItemXProveedorResource itemXProveedor = new ItemXProveedorResource();
		itemXProveedor.setItem(item);
		itemXProveedor.setProveedor(proveedor);
		itemXProveedor.setPrecio(precio);
		
		return itemXProveedor;
	}

}
