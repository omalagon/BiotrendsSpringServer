package com.biotrends.entities.pedido;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.itemxproveedor.ItemXProveedor;
import com.biotrends.entities.ordencompra.OrdenCompra;
import com.biotrends.entities.recepcion.Recepcion;
import com.biotrends.entities.solicitud.Solicitud;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author Oscar Malagon
 * @since 10/01/2017.
 */
@Data
@Entity
@Table(name = "BIO_PEDIDO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Pedido extends BiotrendsBaseEntity{

    private static final long serialVersionUID = -2035824732869046210L;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PED_REC_ID",foreignKey = @ForeignKey(name = "FK_PED_REC_ID"))
    private Recepcion recepcion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PED_OCOMPRA_ID",foreignKey = @ForeignKey(name = "FK_PED_OCOMPRA_ID"))
    private OrdenCompra ordenCompra;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PED_SOLIC_ID",foreignKey = @ForeignKey(name = "FK_PED_SOLIC_ID"))
    private Solicitud solicitud;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemXProveedor> itemXProveedorList;

    @Builder
    public static Pedido target(String id, Recepcion recepcion, OrdenCompra ordenCompra, Solicitud solicitud, List<ItemXProveedor> itemXProveedorList ){
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setRecepcion(recepcion);
        pedido.setOrdenCompra(ordenCompra);
        pedido.setSolicitud(solicitud);
        pedido.setItemXProveedorList(itemXProveedorList);

        return pedido;
    }

}
