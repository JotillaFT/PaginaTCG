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
 * Relación entre una impresión y uno de sus ilustradores acreditados.
 */
@Entity
@Table(name = "impresion_carta_ilustrador")
public class ImpresionCartaIlustrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "impresion_carta_id", nullable = false)
    private ImpresionCarta impresionCarta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ilustrador_id", nullable = false)
    private Ilustrador ilustrador;

    /**
     * Posición del ilustrador dentro del crédito oficial.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    protected ImpresionCartaIlustrador() {
    }

    public ImpresionCartaIlustrador(
            ImpresionCarta impresionCarta,
            Ilustrador ilustrador,
            Integer orden
    ) {
        this.impresionCarta = impresionCarta;
        this.ilustrador = ilustrador;
        this.orden = orden;
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

    public Ilustrador getIlustrador() {
        return ilustrador;
    }

    public void setIlustrador(Ilustrador ilustrador) {
        this.ilustrador = ilustrador;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}