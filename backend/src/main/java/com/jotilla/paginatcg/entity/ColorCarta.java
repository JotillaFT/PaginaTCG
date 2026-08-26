package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Color reutilizable del catálogo de cartas.
 *
 * <p>Se corresponde con la tabla MySQL {@code color}. Sus filas representan
 * códigos internos estables como {@code RED} o {@code BLUE}; la relación con
 * secciones se guarda en {@link SeccionCartaColor} para admitir cartas de uno
 * o varios colores sin crear columnas específicas para cada color.</p>
 *
 * <p>Se usará para filtros por color y combinaciones de colores. La interfaz
 * podrá traducir estos códigos sin cambiar el modelo interno.</p>
 */
@Entity
@Table(name = "color")
public class ColorCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código interno del color. El catálogo se controla mediante migraciones,
     * por eso la entidad no expone setter para este campo.
     */
    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    protected ColorCarta() {
    }

    public ColorCarta(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }
}
