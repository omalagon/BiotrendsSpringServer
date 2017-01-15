package com.biotrends.entities.proveedor;


import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.itemxproveedor.ItemXProveedor;
import com.biotrends.entities.proveedor.evaluacion.Evaluacion;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Oscar Malagon
 * @since 4/12/2016.
 */

@Data
@Entity
@Table(name = "BIO_PROVEEDOR")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Proveedor extends BiotrendsBaseEntity{

    private static final long serialVersionUID = -6608617091315373335L;

    @Column(length= 100, name = "PROV_NOM")
    @NotNull
    private String nombre;

    @Column(length= 100, name = "PROV_DIR")
    @NotNull
    private String direccion;

    @Column(length= 100, name = "PROV_CORR")
    @NotNull
    private String correo;

    @Column(length= 100, name = "PROV_TEL")
    @NotNull
    private String telefono;

    @Column(length= 100, name = "PROV_FAX")
    @NotNull
    private String fax;

    @Column(length= 100, name = "PROV_CEL")
    @NotNull
    private String celular;

    @Column(length= 100, name = "PROV_CONT")
    @NotNull
    private String contacto;

    @Column(length= 100, name = "PROV_CIU")
    @NotNull
    private String ciudad;

    @OneToMany(mappedBy = "proveedor")
    private List<ItemXProveedor> itemsXProveedor;

    @OneToMany(mappedBy = "proveedor")
    private List<Evaluacion> evaluaciones;

    @Builder
    public static Proveedor target(String id, String nombre, String direccion, String telefono, String fax,
        String celular, String contacto, String ciudad,
        List<ItemXProveedor> itemsXProveedor,List<Evaluacion> evaluaciones){
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setFax(fax);
        proveedor.setCelular(celular);
        proveedor.setContacto(contacto);
        proveedor.setCiudad(ciudad);
        proveedor.setItemsXProveedor(itemsXProveedor);
        proveedor.setEvaluaciones(evaluaciones);

        return proveedor;
    }

}
