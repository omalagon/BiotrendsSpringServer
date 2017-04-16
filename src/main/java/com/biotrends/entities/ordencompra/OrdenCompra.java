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
public class OrdenCompra extends BiotrendsBaseEntity{

    private static final long serialVersionUID = -4612995550087076042L;

    @Column(name = "ORD_NUM_ORDEN")
    Long numeroOrden;
    
    @Column(length= 500, name = "ORD_OBSERVACIONES")
    @NotNull
    private String observaciones;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORD_ID_USU",foreignKey = @ForeignKey(name = "FK_ORD_ID_USU"))
    private Usuario usuario;

    @Builder
    public static OrdenCompra target(String id, 
    		String observaciones, 
    		Usuario usuario,
    		Long numeroOrden){
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setId(id);
        ordenCompra.setObservaciones(observaciones);
        ordenCompra.setUsuario(usuario);
        ordenCompra.setNumeroOrden(numeroOrden);
        
        return ordenCompra;
    }

}
