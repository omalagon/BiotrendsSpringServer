package com.biotrends.entities.solicitud;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.biotrends.entities.BiotrendsBaseEntity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Column(name = "SOL_ID_USU_SOLICITANTE")
    private String solicitante;

    @Column(name = "SOL_ID_USU_AUX_OFI")
    private String auxiliarOficina;

    @Builder
    public static Solicitud target(Date fechaSolicitud, String observaciones, Boolean esRevisado, String solicitante, String auxiliarOficina ){
        Solicitud solicitud = new Solicitud();
        solicitud.setFechaSolicitud(fechaSolicitud);
        solicitud.setObservaciones(observaciones);
        solicitud.setEsRevisado(esRevisado);
        solicitud.setSolicitante(solicitante);
        solicitud.setAuxiliarOficina(auxiliarOficina);

        return solicitud;
    }
}
