package com.biotrends.entities.solicitud;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.repositories.solicitud.SolicitudRepository;

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
@SequenceGenerator(name="idSolicitudGenerator", initialValue = 1)
public class Solicitud extends BiotrendsBaseEntity {

    private static final long serialVersionUID = -6721994853780787846L;
    
    @Column(name = "SOL_ID_SOL")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "idSolicitudGenerator")
    private Long idSolicitud;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ItemXSolicitud> itmxsol;

    @Builder
    public static Solicitud target(
    		Long idSolicitud,
    		Date fechaSolicitud,
    		String observaciones, 
    		Boolean esRevisado, 
    		String solicitante, 
    		String auxiliarOficina,
    		Set<ItemXSolicitud> itmxsol){
    	
        Solicitud solicitud = new Solicitud();
        solicitud.setIdSolicitud(idSolicitud);
        solicitud.setFechaSolicitud(fechaSolicitud);
        solicitud.setObservaciones(observaciones);
        solicitud.setEsRevisado(esRevisado);
        solicitud.setSolicitante(solicitante);
        solicitud.setAuxiliarOficina(auxiliarOficina);
        solicitud.setItmxsol(itmxsol);
        
        return solicitud;
    }
}
