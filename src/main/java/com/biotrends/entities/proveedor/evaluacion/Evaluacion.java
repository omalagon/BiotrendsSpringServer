package com.biotrends.entities.proveedor.evaluacion;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.proveedor.Proveedor;
import com.biotrends.entities.recepcion.Recepcion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Oscar Malagon
 * @since 6/01/2017.
 */
@Data
@Entity
@Table(name = "BIO_PROV_EV")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Evaluacion extends BiotrendsBaseEntity {

    private static final long serialVersionUID = -7751736715883179304L;

    @Column(name = "EV_PREGUNTA", length = 100)
    private String pregunta;

    @Column(name = "EV_RESPUESTA")
    private Boolean respuesta;

    @ManyToOne
    @JoinColumn(name = "PROV_EV_PROV_ID", foreignKey = @ForeignKey(name = "FK_PROV_EV_PROV_ID"))
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "PROV_EV_REC_ID", foreignKey = @ForeignKey(name = "FK_PROV_REC_PROV_ID"))
    private Recepcion recepcion;

}
