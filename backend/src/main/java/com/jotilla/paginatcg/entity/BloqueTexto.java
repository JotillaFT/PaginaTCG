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

/**
 * Caja oficial completa de texto asociada a una sección de carta.
 *
 * <p>Se corresponde con la tabla MySQL {@code bloque_texto}. Un bloque conserva
 * el texto oficial completo en inglés sin partir cada efecto individual. Las
 * etiquetas de activación y las palabras clave propias se relacionan mediante
 * tablas externas para poder filtrar sin perder el texto original.</p>
 *
 * <p>Se utilizará para filtros por categoría de bloque, presencia de etiquetas
 * de efecto, palabras clave directas y búsqueda textual en
 * {@code contenido_oficial}.</p>
 */
@Entity
@Table(name = "bloque_texto")
public class BloqueTexto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seccion_carta_id", nullable = false)
    private SeccionCarta seccionCarta;

    /**
     * Tipo de caja de texto, como efecto normal, heredado, seguridad, regla o
     * Link. No es una etiqueta de activación como {@code [Main]}.
     */
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "categoria_bloque", nullable = false, length = 50)
    private CategoriaBloqueTexto categoriaBloque;

    /**
     * Posición del bloque dentro de la sección para conservar el orden oficial
     * de las cajas de texto.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    /**
     * Texto oficial completo en inglés. Es la fuente de verdad para menciones,
     * concesiones y casos que todavía no se modelen de forma estructurada.
     */
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
