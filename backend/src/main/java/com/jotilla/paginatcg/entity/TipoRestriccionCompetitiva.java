package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "tipo_restriccion_competitiva",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_tipo_restriccion_competitiva_codigo",
                        columnNames = "codigo"
                ),
                @UniqueConstraint(
                        name = "uk_tipo_restriccion_competitiva_nombre",
                        columnNames = "nombre_oficial"
                )
        }
)
public class TipoRestriccionCompetitiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    @Column(name = "nombre_oficial", nullable = false, length = 100)
    private String nombreOficial;

    protected TipoRestriccionCompetitiva() {
    }

    public TipoRestriccionCompetitiva(
            String codigo,
            String nombreOficial
    ) {
        this.codigo = codigo;
        this.nombreOficial = nombreOficial;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreOficial() {
        return nombreOficial;
    }

    public void setNombreOficial(String nombreOficial) {
        this.nombreOficial = nombreOficial;
    }
}