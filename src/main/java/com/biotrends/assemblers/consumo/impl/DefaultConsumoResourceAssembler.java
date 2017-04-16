package com.biotrends.assemblers.consumo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biotrends.assemblers.consumo.ConsumoResourceAssembler;
import com.biotrends.assemblers.consumo.ConsumoResource;
import com.biotrends.assemblers.item.ItemResource;
import com.biotrends.assemblers.item.ItemResourceAssembler;
import com.biotrends.entities.consumo.Consumo;
import com.biotrends.entities.item.Item;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 *
 */
@Service
public class DefaultConsumoResourceAssembler implements ConsumoResourceAssembler {

	private final ItemResourceAssembler itemAssembler;
	
	@Autowired
	public DefaultConsumoResourceAssembler(ItemResourceAssembler itemAssembler) {
		this.itemAssembler = itemAssembler;
	}
	
	@Override
	public ConsumoResource toResource(Consumo entity) {
		if(entity != null){
			ItemResource item = itemAssembler.toResource(entity.getItem());
			
			return ConsumoResource.builder()
					.fechaDescargo(entity.getFechaDescargo())
					.area(entity.getArea())
					.cantidad(entity.getCantidad())
					.item(item)
					.usuario(entity.getUsuario())
					.build();
		}
		return null;
	}

	@Override
	public Consumo fromResource(ConsumoResource resource) {
		if(resource != null){
			
			Item item = itemAssembler.fromResource(resource.getItem());
			
			return Consumo.builder()
					.fechaDescargo(resource.getFechaDescargo())
					.area(resource.getArea())
					.cantidad(resource.getCantidad())
					.item(item)
					.usuario(resource.getUsuario())
					.build();
		}
		return null;
	}

}
