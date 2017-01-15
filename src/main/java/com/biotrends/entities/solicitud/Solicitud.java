package com.biotrends.entities.solicitud;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.usuario.Usuario;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Oscar Malagon
 * @since 3/01/2017.
 */
@Data
@Entity
@Table(name = "BIO_SOLICITUD")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Solicitud extends BiotrendsBaseEntity {

    private static final long serialVersionUID = -6721994853780787846L;
    
    @Column(length= 100, name = "SOL_FECHA")
    @NotNull
    private Date fechaSolicitud;

    @Column(length = 500, name = "SOL_OBS")
    private String observaciones;

    @Column(name = "SOL_REVISADO")
    private Boolean esRevisado;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_USU_SOLICITANTE", nullable = false, foreignKey = @ForeignKey(name = "FK_ID_USU_SOLICITANTE"))
    private Usuario solicitante;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "ID_USU_AUX_OFI", nullable = true, foreignKey = @ForeignKey(name = "FK_ID_USU_AUX_OFI"))
    private Usuario auxiliarOficina;

    @Builder
    public static Solicitud target(Date fechaSolicitud, String observaciones, Boolean esRevisado, Usuario solicitante, Usuario auxiliarOficina ){
        Solicitud solicitud = new Solicitud();
        solicitud.setFechaSolicitud(fechaSolicitud);
        solicitud.setObservaciones(observaciones);
        solicitud.setEsRevisado(esRevisado);
        solicitud.setSolicitante(solicitante);
        solicitud.setAuxiliarOficina(auxiliarOficina);

        return solicitud;
    }
}
