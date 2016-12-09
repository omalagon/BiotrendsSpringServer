package com.biotrends.entities.usuario;


import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.item.Item;
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
@Table(name = "BIO_USUARIO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Usuario extends BiotrendsBaseEntity{

    @Column(length= 100, name = "USU_NOMBRE")
    @NotNull
    private String nombre;

    @Column(length= 100, name = "USU_PASS")
    @NotNull
    private String password;

    @Column(length= 100, name = "USU_CORREO")
    @NotNull
    private String correo;

    @Column(length= 100, name = "USU_LAB")
    @NotNull
    private String laboratorio;

    @Builder
    public Usuario target(String nombre, String password, String correo, String laboratorio){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(password);
        usuario.setCorreo(correo);
        usuario.setLaboratorio(laboratorio);

        return usuario;
    }

}
