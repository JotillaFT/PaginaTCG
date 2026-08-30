package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Relación entre un requisito normal de evolución y uno de sus colores
 * concretos admitidos.
 *
 * <p>Se corresponde con la tabla MySQL
 * {@code requisito_evolucion_normal_color}. Varias filas permiten expresar
 * que un mismo requisito admite diferentes colores manteniendo el orden
 * oficial en el que aparecen.</p>
 *
 * <p>No se crean relaciones de color cuando
 * {@link RequisitoEvolucionNormal#isCualquierColor()} es {@code true}, ni
 * cuando el requisito no exige ningún color.</p>
 */
@Entity
@Table(name = "requisito_evolucion_normal_color")
public class RequisitoEvolucionNormalColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Requisito al que pertenece el color admitido.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requisito_evolucion_normal_id", nullable = false)
    private RequisitoEvolucionNormal requisitoEvolucionNormal;

    /**
     * Color concreto admitido por el requisito.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "color_id", nullable = false)
    private ColorCarta color;

    /**
     * Posición del color dentro del requisito oficial.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    protected RequisitoEvolucionNormalColor() {
    }

    public RequisitoEvolucionNormalColor(
            RequisitoEvolucionNormal requisitoEvolucionNormal,
            ColorCarta color,
            Integer orden
    ) {
        this.requisitoEvolucionNormal = requisitoEvolucionNormal;
        this.color = color;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public RequisitoEvolucionNormal getRequisitoEvolucionNormal() {
        return requisitoEvolucionNormal;
    }

    public void setRequisitoEvolucionNormal(
            RequisitoEvolucionNormal requisitoEvolucionNormal
    ) {
        this.requisitoEvolucionNormal = requisitoEvolucionNormal;
    }

    public ColorCarta getColor() {
        return color;
    }

    public void setColor(ColorCarta color) {
        this.color = color;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}