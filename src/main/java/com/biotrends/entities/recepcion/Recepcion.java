package com.biotrends.entities.recepcion;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.item.Item;
import com.biotrends.entities.item.ItemBase;
import com.biotrends.entities.proveedor.evaluacion.Evaluacion;
import com.biotrends.entities.usuario.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Oscar Malagon
 * @since 6/01/2017.
 */
@Data
@Entity
@Table(name = "BIO_RECEPCION")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Recepcion extends BiotrendsBaseEntity{

    private static final long serialVersionUID = -90984896691195505L;

    @Column(length= 100, name = "REC_FEC_LLEG")
    @NotNull
    private Date fechaLlegada;

    @Column(length= 100, name = "REC_FEC_VENC")
    @Future
    @NotNull
    private Date fechaVencimiento;

    @Column(name = "REC_MVER", length = 200)
    private String mVerificacion;

    @Column(length = 500, name = "REC_OBS")
    private String observaciones;

    @Embedded
    @Column(nullable = false)
    private ItemBase itemBase;

    @OneToMany(mappedBy = "recepcion")
    private List<Evaluacion> evaluaciones;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "REC_ID_USU",foreignKey = @ForeignKey(name = "FK_REC_ID_USU"))
    private Usuario usuario;

    public static Recepcion target(String id, Date fechaLlegada, Date fechaVencimiento, String mVerificacion,
        String observaciones, ItemBase itemBase, List<Evaluacion> evaluaciones, Usuario usuario){
        Recepcion recepcion = new Recepcion();
        recepcion.setId(id);
        recepcion.setFechaLlegada(fechaLlegada);
        recepcion.setFechaVencimiento(fechaVencimiento);
        recepcion.setMVerificacion(mVerificacion);
        recepcion.setObservaciones(observaciones);
        recepcion.setItemBase(itemBase);
        recepcion.setEvaluaciones(evaluaciones);
        recepcion.setUsuario(usuario);

        return recepcion;
    }


}
