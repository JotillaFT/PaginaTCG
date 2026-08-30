package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "restriccion_competitiva_carta",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_restriccion_competitiva_carta_inicio",
                        columnNames = {"carta_id", "fecha_inicio"}
                )
        }
)
public class RestriccionCompetitivaCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carta_id", nullable = false)
    private Carta carta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "tipo_restriccion_competitiva_id",
            nullable = false
    )
    private TipoRestriccionCompetitiva tipoRestriccionCompetitiva;

    @Column(name = "maximo_copias", nullable = false)
    private Integer maximoCopias;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "nota", columnDefinition = "TEXT")
    private String nota;

    protected RestriccionCompetitivaCarta() {
    }

    public RestriccionCompetitivaCarta(
            Carta carta,
            TipoRestriccionCompetitiva tipoRestriccionCompetitiva,
            Integer maximoCopias,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            String nota
    ) {
        this.carta = carta;
        this.tipoRestriccionCompetitiva = tipoRestriccionCompetitiva;
        this.maximoCopias = maximoCopias;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public TipoRestriccionCompetitiva getTipoRestriccionCompetitiva() {
        return tipoRestriccionCompetitiva;
    }

    public void setTipoRestriccionCompetitiva(
            TipoRestriccionCompetitiva tipoRestriccionCompetitiva
    ) {
        this.tipoRestriccionCompetitiva = tipoRestriccionCompetitiva;
    }

    public Integer getMaximoCopias() {
        return maximoCopias;
    }

    public void setMaximoCopias(Integer maximoCopias) {
        this.maximoCopias = maximoCopias;
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