package com.biotrends.entities.itemxproveedor;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Oscar Malagon
 * @since 6/01/2017.
 */
@Embeddable
@Data
@EqualsAndHashCode
public class ItemXProveedorId implements Serializable{

    private static final long serialVersionUID = -5032218498494258860L;

    private String item;
    private String proveedor;

}
