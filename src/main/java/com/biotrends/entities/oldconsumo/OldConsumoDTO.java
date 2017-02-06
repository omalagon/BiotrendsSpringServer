package com.biotrends.entities.oldconsumo;

import java.io.Serializable;

import com.biotrends.entities.item.Item;

import lombok.Builder;
import lombok.Data;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
@Data
public class OldConsumoDTO implements Serializable{

    private static final long serialVersionUID = -613385108473851367L;

    private Item item;
    private Double totalCompras;
    private Double totalDescargos;

    @Builder
    public static OldConsumoDTO target(Item item, Double totalCompras, Double totalDescargos ){
        OldConsumoDTO oldConsumoDTO = new OldConsumoDTO();
        oldConsumoDTO.setItem(item);
        oldConsumoDTO.setTotalCompras(totalCompras);
        oldConsumoDTO.setTotalDescargos(totalDescargos);

        return oldConsumoDTO;
    }
}
