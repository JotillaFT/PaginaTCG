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
 * Relación entre un requisito Link y uno de sus rasgos oficiales admitidos.
 *
 * <p>Se corresponde con la tabla MySQL
 * {@code requisito_link_rasgo}. Varias filas permiten conservar varios
 * valores aceptados y el orden oficial en el que aparecen.</p>
 *
 * <p>Esta relación representa el valor exigido por el requisito Link. No
 * implica por sí misma que todas las secciones que cumplen la condición
 * deban poseer una fila equivalente en {@link SeccionCartaRasgo}. Por
 * ejemplo, el valor {@code Appmon} también puede estar expresado mediante
 * la forma oficial de la sección.</p>
 */
@Entity
@Table(name = "requisito_link_rasgo")
public class RequisitoLinkRasgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Requisito Link al que pertenece el rasgo admitido.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requisito_link_id", nullable = false)
    private RequisitoLink requisitoLink;

    /**
     * Rasgo oficial exigido por el requisito, por ejemplo
     * {@code Appmon} o {@code TS}.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rasgo_id", nullable = false)
    private Rasgo rasgo;

    /**
     * Posición del rasgo dentro del requisito oficial.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    protected RequisitoLinkRasgo() {
    }

    public RequisitoLinkRasgo(
            RequisitoLink requisitoLink,
            Rasgo rasgo,
            Integer orden
    ) {
        this.requisitoLink = requisitoLink;
        this.rasgo = rasgo;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public RequisitoLink getRequisitoLink() {
        return requisitoLink;
    }

    public void setRequisitoLink(RequisitoLink requisitoLink) {
        this.requisitoLink = requisitoLink;
    }

    public Rasgo getRasgo() {
        return rasgo;
    }

    public void setRasgo(Rasgo rasgo) {
        this.rasgo = rasgo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}