package com.jotilla.paginatcg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Relación entre una impresión concreta y un lanzamiento en el que aparece.
 */
@Entity
@Table(name = "impresion_carta_lanzamiento")
public class ImpresionCartaLanzamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "impresion_carta_id", nullable = false)
    private ImpresionCarta impresionCarta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lanzamiento_id", nullable = false)
    private Lanzamiento lanzamiento;

    protected ImpresionCartaLanzamiento() {
    }

    public ImpresionCartaLanzamiento(
            ImpresionCarta impresionCarta,
            Lanzamiento lanzamiento
    ) {
        this.impresionCarta = impresionCarta;
        this.lanzamiento = lanzamiento;
    }

    public Long getId() {
        return id;
    }

    public ImpresionCarta getImpresionCarta() {
        return impresionCarta;
    }

    public void setImpresionCarta(ImpresionCarta impresionCarta) {
        this.impresionCarta = impresionCarta;
    }

    public Lanzamiento getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(Lanzamiento lanzamiento) {
        this.lanzamiento = lanzamiento;
    }
}