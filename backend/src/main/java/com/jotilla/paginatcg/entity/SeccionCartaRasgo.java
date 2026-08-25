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

@Entity
@Table(name = "seccion_carta_rasgo")
public class SeccionCartaRasgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seccion_carta_id", nullable = false)
    private SeccionCarta seccionCarta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rasgo_id", nullable = false)
    private Rasgo rasgo;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    protected SeccionCartaRasgo() {
    }

    public SeccionCartaRasgo(
            SeccionCarta seccionCarta,
            Rasgo rasgo,
            Integer orden
    ) {
        this.seccionCarta = seccionCarta;
        this.rasgo = rasgo;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public SeccionCarta getSeccionCarta() {
        return seccionCarta;
    }

    public void setSeccionCarta(SeccionCarta seccionCarta) {
        this.seccionCarta = seccionCarta;
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