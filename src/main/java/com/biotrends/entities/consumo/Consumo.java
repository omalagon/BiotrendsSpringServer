package com.biotrends.entities.consumo;


import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.item.Item;
import com.biotrends.entities.usuario.Usuario;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Oscar Malagon
 * @since 4/12/2016.
 */

@Data
@Entity
@Table(name = "BIO_CONSUMO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Consumo extends BiotrendsBaseEntity{

    private static final long serialVersionUID = -5138569755832797011L;

    @Column(length= 100, name = "CONS_FECHA")
    @NotNull
    private Date fechaDescargo;

    @Column(length= 100, name = "CONS_AREA")
    @NotNull
    private String area;

    @Column(name = "CONS_CANT")
    @NotNull
    private Double cantidad;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CONS_ID_ITEM",foreignKey = @ForeignKey(name = "FK_CONS_ID_ITEM"))
    private Item item;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CONS_ID_USU",foreignKey = @ForeignKey(name = "FK_CONS_ID_USU"))
    private Usuario usuario;

    @Builder
    public static Consumo target(String id, Date fechaDescargo, String area,Double cantidad, Item item, Usuario usuario){
        Consumo consumo = new Consumo();
        consumo.setId(id);
        consumo.setFechaDescargo(fechaDescargo);
        consumo.setArea(area);
        consumo.setCantidad(cantidad);
        consumo.setItem(item);
        consumo.setUsuario(usuario);

        return consumo;
    }

}