package com.biotrends.entities.ordencompra;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.usuario.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Oscar Malagon
 * @since 8/12/2016.
 */
@Data
@Entity
@Table(name = "BIO_ORDEN_COMPRA")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class OrdenCompra extends BiotrendsBaseEntity{

    @Column(length= 500, name = "ORD_OBSERVACIONES")
    @NotNull
    private String observaciones;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORD_ID_USU",foreignKey = @ForeignKey(name = "ORD_ID_USU"))
    private Usuario usuario;

    @Builder
    public OrdenCompra target(String id, String observaciones, Usuario usuario){
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setId(id); //Numero de orden
        ordenCompra.setObservaciones(observaciones);
        ordenCompra.setUsuario(usuario);

        return ordenCompra;
    }

}
