package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

/**
 * Etiqueta oficial de activación o temporización presente en un bloque de texto.
 *
 * <p>Se corresponde con la tabla MySQL {@code etiqueta_efecto}. Es un catálogo
 * extensible y no un enum, porque las etiquetas oficiales pueden ampliarse o
 * ajustarse al analizar nuevos textos importados.</p>
 *
 * <p>Se relaciona con {@link BloqueTexto} mediante {@link BloqueTextoEtiqueta}
 * para indicar presencia de etiquetas como {@code [Main]}, {@code [Delay]} o
 * {@code [On Play]} sin dividir el contenido oficial en frases individuales.</p>
 *
 * <p>No representa clasificaciones semánticas inferidas, como reducciones de
 * coste, acciones gratuitas o requisitos ignorados. Esos casos se conservan en
 * el texto oficial y se localizarán mediante búsqueda textual.</p>
 *
 * <p>Se usará para filtros por etiquetas oficiales presentes dentro de cada
 * categoría de bloque.</p>
 */
@Entity
@Table(name = "etiqueta_efecto")
public class EtiquetaEfecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código interno normalizado para usar en filtros y lógica de aplicación.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String codigo;

    /**
     * Nombre oficial o etiqueta visible según el texto de las cartas.
     */
    @Column(name = "nombre_oficial", nullable = false, unique = true, length = 100)
    private String nombreOficial;

    protected EtiquetaEfecto() {
    }

    /**
     * Crea una etiqueta de efecto válida para el catálogo extensible.
     */
    public EtiquetaEfecto(String codigo, String nombreOficial) {
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
