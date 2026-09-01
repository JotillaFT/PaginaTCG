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
 * Relación entre una errata oficial y una impresión física que contiene el
 * texto incorrecto.
 *
 * <p>Se corresponde con la tabla MySQL {@code errata_impresion_carta}. Esta
 * relación permite distinguir entre la existencia histórica de una errata en la
 * carta funcional y el hecho de que una impresión concreta muestre físicamente
 * el error.</p>
 */
@Entity
@Table(name = "errata_impresion_carta")
public class ErrataImpresionCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Errata oficial que explica el cambio de texto.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "errata_carta_id", nullable = false)
    private ErrataCarta errataCarta;

    /**
     * Impresión física o arte concreto que contiene el texto erróneo.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "impresion_carta_id", nullable = false)
    private ImpresionCarta impresionCarta;

    /**
     * Nota opcional sobre la afectación física de esta impresión.
     */
    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    protected ErrataImpresionCarta() {
    }

    public ErrataImpresionCarta(
            ErrataCarta errataCarta,
            ImpresionCarta impresionCarta
    ) {
        this.errataCarta = errataCarta;
        this.impresionCarta = impresionCarta;
    }

    public Long getId() {
        return id;
    }

    public ErrataCarta getErrataCarta() {
        return errataCarta;
    }

    public void setErrataCarta(ErrataCarta errataCarta) {
        this.errataCarta = errataCarta;
    }

    public ImpresionCarta getImpresionCarta() {
        return impresionCarta;
    }

    public void setImpresionCarta(ImpresionCarta impresionCarta) {
        this.impresionCarta = impresionCarta;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
