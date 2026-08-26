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
 * Relación entre una sección de carta y uno de sus colores.
 *
 * <p>Se corresponde con la tabla MySQL {@code seccion_carta_color}. Permite
 * representar secciones multicolor reutilizando el catálogo {@link ColorCarta}
 * y evitando columnas como color1, color2 o listas separadas por comas.</p>
 *
 * <p>Se usará para filtros por color, incluyendo búsquedas de "contiene todos",
 * "contiene cualquiera" o "exactamente esta combinación".</p>
 */
@Entity
@Table(name = "seccion_carta_color")
public class SeccionCartaColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seccion_carta_id", nullable = false)
    private SeccionCarta seccionCarta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "color_id", nullable = false)
    private ColorCarta color;

    /**
     * Posición del color dentro del orden oficial de la sección.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    protected SeccionCartaColor() {
    }

    public SeccionCartaColor(
            SeccionCarta seccionCarta,
            ColorCarta color,
            Integer orden
    ) {
        this.seccionCarta = seccionCarta;
        this.color = color;
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

    public ColorCarta getColor() {
        return color;
    }

    public void setColor(ColorCarta color) {
        this.color = color;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
