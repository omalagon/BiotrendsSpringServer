package com.biotrends.entities.usuario;


import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.consumo.Consumo;
import com.biotrends.entities.ordencompra.OrdenCompra;
import com.biotrends.entities.recepcion.Recepcion;
import com.biotrends.entities.solicitud.Solicitud;
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
@Table(name = "BIO_USUARIO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Usuario extends BiotrendsBaseEntity{

    private static final long serialVersionUID = 1794736964464582948L;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( name = "USU_ID_CREADOR", foreignKey = @ForeignKey(name = "FK_USU_ID_CREADOR"))
    @NotNull
    private Usuario usuarioCreador;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solicitante")
    private List<Solicitud> solicitudesPedidas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auxiliarOficina")
    private List<Solicitud> solicitudesAprobadas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Consumo> consumos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<OrdenCompra> ordenesDeCompra;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Recepcion> recepciones;

    @Builder
    public static Usuario target(String nombre,
        String password,
        String correo,
        String laboratorio,
        Usuario usuarioCreador,
        List<Solicitud> solicitudesPedidas,
        List<Solicitud> solicitudesAprobadas,
        List<Consumo> consumos,
        List<OrdenCompra> ordenesDeCompra,
        List<Recepcion> recepciones){

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(password);
        usuario.setCorreo(correo);
        usuario.setLaboratorio(laboratorio);
        usuario.setUsuarioCreador(usuarioCreador);
        usuario.setSolicitudesPedidas(solicitudesPedidas);
        usuario.setSolicitudesAprobadas(solicitudesAprobadas);
        usuario.setConsumos(consumos);
        usuario.setOrdenesDeCompra(ordenesDeCompra);
        usuario.setRecepciones(recepciones);

        return usuario;
    }

}
