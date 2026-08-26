package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

/**
 * Palabra clave oficial del Digimon Card Game.
 *
 * <p>Se corresponde con la tabla MySQL {@code palabra_clave}. Es un catálogo
 * extensible y no un enum, porque las palabras clave del juego pueden crecer
 * con nuevas mecánicas.</p>
 *
 * <p>Se relaciona con {@link BloqueTexto} mediante
 * {@link BloqueTextoPalabraClave} solo cuando el bloque presenta directamente
 * una palabra clave propia de esa sección, como {@code <Blocker>},
 * {@code <Rush>} o {@code <Jamming>}. Menciones, concesiones, eliminaciones,
 * negaciones o usos como condición se buscarán en {@code contenido_oficial},
 * incluso si la concesión afecta a la propia sección.</p>
 *
 * <p>Se usará para filtros por palabras clave propias a nivel de bloque o
 * sección, separados de búsquedas textuales sobre menciones o concesiones.</p>
 */
@Entity
@Table(name = "palabra_clave")
public class PalabraClave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código interno normalizado para usar en filtros y lógica de aplicación.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String codigo;

    /**
     * Nombre oficial de la palabra clave tal como aparece en el texto.
     */
    @Column(name = "nombre_oficial", nullable = false, unique = true, length = 100)
    private String nombreOficial;

    protected PalabraClave() {
    }

    /**
     * Crea una palabra clave válida para el catálogo extensible.
     */
    public PalabraClave(String codigo, String nombreOficial) {
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
