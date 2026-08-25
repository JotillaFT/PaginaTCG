package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "bloque_texto")
public class BloqueTexto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seccion_carta_id", nullable = false)
    private SeccionCarta seccionCarta;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "categoria_bloque", nullable = false, length = 50)
    private CategoriaBloqueTexto categoriaBloque;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(
            name = "contenido_oficial",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String contenidoOficial;

    protected BloqueTexto() {
    }

    public BloqueTexto(
            SeccionCarta seccionCarta,
            CategoriaBloqueTexto categoriaBloque,
            Integer orden,
            String contenidoOficial
    ) {
        this.seccionCarta = seccionCarta;
        this.categoriaBloque = categoriaBloque;
        this.orden = orden;
        this.contenidoOficial = contenidoOficial;
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

    public CategoriaBloqueTexto getCategoriaBloque() {
        return categoriaBloque;
    }

    public void setCategoriaBloque(CategoriaBloqueTexto categoriaBloque) {
        this.categoriaBloque = categoriaBloque;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getContenidoOficial() {
        return contenidoOficial;
    }

    public void setContenidoOficial(String contenidoOficial) {
        this.contenidoOficial = contenidoOficial;
    }
}