package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "restriccion_pareja",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_restriccion_pareja_cartas_inicio",
                        columnNames = {
                                "carta_a_id",
                                "carta_b_id",
                                "fecha_inicio"
                        }
                )
        }
)
public class RestriccionPareja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carta_a_id", nullable = false)
    private Carta cartaA;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carta_b_id", nullable = false)
    private Carta cartaB;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "nota", columnDefinition = "TEXT")
    private String nota;

    protected RestriccionPareja() {
    }

    public RestriccionPareja(
            Carta cartaA,
            Carta cartaB,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            String nota
    ) {
        this.cartaA = cartaA;
        this.cartaB = cartaB;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public Carta getCartaA() {
        return cartaA;
    }

    public void setCartaA(Carta cartaA) {
        this.cartaA = cartaA;
    }

    public Carta getCartaB() {
        return cartaB;
    }

    public void setCartaB(Carta cartaB) {
        this.cartaB = cartaB;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}