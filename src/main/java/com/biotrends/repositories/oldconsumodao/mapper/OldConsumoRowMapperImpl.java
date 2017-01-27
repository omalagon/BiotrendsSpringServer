package com.biotrends.repositories.oldconsumodao.mapper;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.item.ItemBase;
import com.biotrends.entities.oldconsumo.OldConsumoDTO;
import com.biotrends.utils.Utils;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
@Service
public class OldConsumoRowMapperImpl implements OldConsumoRowMapper {

    private static final String CINTERNO = "cinterno";
    private static final String INVENTARIO = "inventario";
    private static final String DESCRIPCION = "descripcion";
    private static final String PRESENTACION = "presentacion";
    private static final String CANTIDAD = "cantidad";
    private static final String PRECIO = "precio";
    private static final String CCALIDAD = "ccalidad";
    private static final String CESP = "cesp";
    private static final String TOT_COMPRAS = "totCompras";
    private static final String TOT_DESCARGOS = "totDescargos";


    @Override
    public OldConsumoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemBase itemBase = ItemBase.builder()
            .inventario(rs.getString(INVENTARIO))
            .descripcion(rs.getString(DESCRIPCION))
            .presentacion(rs.getString(PRESENTACION))
            .cantidad(rs.getDouble(CANTIDAD))
            .precio(rs.getDouble(PRECIO))
            .cCalidad(Utils.stringToBoolean(rs.getString(CCALIDAD)))
            .cEsp(Utils.stringToBoolean(rs.getString(CESP)))
            .build();

        Item item = Item.builder()
            .id(rs.getString(CINTERNO))
            .itemBase(itemBase)
            .build();

        return OldConsumoDTO.builder()
            .item(item)
            .totalCompras(rs.getDouble(TOT_COMPRAS))
            .totalDescargos(rs.getDouble(TOT_DESCARGOS))
            .build();
    }


}
