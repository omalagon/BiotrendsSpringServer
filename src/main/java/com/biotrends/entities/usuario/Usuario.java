package com.biotrends.entities.usuario;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.consumo.Consumo;
import com.biotrends.entities.ordencompra.OrdenCompra;
import com.biotrends.entities.recepcion.Recepcion;
import com.biotrends.entities.solicitud.Solicitud;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 4/12/2016.
 */

@Data
@Entity
@Table(name = "BIO_USUARIO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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

    @Column(length= 100, name = "USU_ID_CREADOR")
    @NotNull
    private String usuarioCreador;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solicitante")
    private Set<Solicitud> solicitudesPedidas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auxiliarOficina")
    private Set<Solicitud> solicitudesAprobadas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<Consumo> consumos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<OrdenCompra> ordenesDeCompra;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    private Set<Recepcion> recepciones;

    @Builder
    public static Usuario target(
        String id,
        String nombre,
        String password,
        String correo,
        String laboratorio,
        String usuarioCreador,
        Set<Solicitud> solicitudesPedidas,
        Set<Solicitud> solicitudesAprobadas,
        Set<Consumo> consumos,
        Set<OrdenCompra> ordenesDeCompra,
        Set<Recepcion> recepciones){

        Usuario usuario = new Usuario();
        usuario.setId(id);
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
