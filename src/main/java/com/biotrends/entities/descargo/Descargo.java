package com.biotrends.entities.descargo;


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
@Table(name = "BIO_DESCARGO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Descargo extends BiotrendsBaseEntity{

    @Column(length= 100, name = "DESC_FECHA")
    @NotNull
    private Date fechaDescargo;

    @Column(length= 100, name = "DESC_AREA")
    @NotNull
    private String area;

    @Column(name = "DESC_CANT")
    @NotNull
    private Double cantidad;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DESC_ID_ITEM",foreignKey = @ForeignKey(name = "DESC_ID_ITEM"))
    private Item item;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DESC_ID_USU",foreignKey = @ForeignKey(name = "DESC_ID_USU"))
    private Usuario usuario;

    @Builder
    public Descargo target(Date fechaDescargo, String area,Double cantidad, Item item, Usuario usuario){
        Descargo descargo = new Descargo();
        descargo.setFechaDescargo(fechaDescargo);
        descargo.setArea(area);
        descargo.setCantidad(cantidad);
        descargo.setItem(item);
        descargo.setUsuario(usuario);

        return descargo;
    }

}
