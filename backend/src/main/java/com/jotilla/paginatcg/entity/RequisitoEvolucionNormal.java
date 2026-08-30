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
 * Requisito normal de evolución de una sección de carta.
 *
 * <p>Se corresponde con la tabla MySQL
 * {@code requisito_evolucion_normal}. Cada fila representa una alternativa
 * oficial completa. Los campos presentes dentro de una misma fila forman
 * conjuntamente una única condición.</p>
 *
 * <p>Las evoluciones especiales escritas dentro del texto oficial, como
 * evoluciones por nombre, rasgo, ADN, Burst o App Fusion, permanecen en
 * {@link BloqueTexto#contenidoOficial} y no se modelan en esta entidad.</p>
 */
@Entity
@Table(name = "requisito_evolucion_normal")
public class RequisitoEvolucionNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Sección que presenta este requisito.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seccion_carta_id", nullable = false)
    private SeccionCarta seccionCarta;

    /**
     * Posición oficial del requisito dentro de la sección.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    /**
     * Categoría de origen únicamente cuando la condición la exige
     * explícitamente, por ejemplo {@link CategoriaCarta#TAMER}.
     */
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "categoria_origen", length = 50)
    private CategoriaCarta categoriaOrigen;

    /**
     * Nivel de origen exigido. Es {@code null} cuando la condición no
     * especifica un nivel.
     */
    @Column(name = "nivel_origen")
    private Integer nivelOrigen;

    /**
     * Forma de origen exigida, por ejemplo {@code Stnd./Appmon}. Es
     * independiente de la forma propia de la sección.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_origen_id")
    private FormaCarta formaOrigen;

    /**
     * Coste oficial de evolución del requisito.
     */
    @Column(name = "coste", nullable = false)
    private Integer coste;

    /**
     * Indica que el requisito admite cualquier color. Cuando es
     * {@code true}, no debe tener relaciones con colores concretos.
     */
    @Column(name = "cualquier_color", nullable = false)
    private boolean cualquierColor = false;

    protected RequisitoEvolucionNormal() {
    }

    public RequisitoEvolucionNormal(
            SeccionCarta seccionCarta,
            Integer orden,
            Integer coste
    ) {
        this.seccionCarta = seccionCarta;
        this.orden = orden;
        this.coste = coste;
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public CategoriaCarta getCategoriaOrigen() {
        return categoriaOrigen;
    }

    public void setCategoriaOrigen(CategoriaCarta categoriaOrigen) {
        this.categoriaOrigen = categoriaOrigen;
    }

    public Integer getNivelOrigen() {
        return nivelOrigen;
    }

    public void setNivelOrigen(Integer nivelOrigen) {
        this.nivelOrigen = nivelOrigen;
    }

    public FormaCarta getFormaOrigen() {
        return formaOrigen;
    }

    public void setFormaOrigen(FormaCarta formaOrigen) {
        this.formaOrigen = formaOrigen;
    }

    public Integer getCoste() {
        return coste;
    }

    public void setCoste(Integer coste) {
        this.coste = coste;
    }

    public boolean isCualquierColor() {
        return cualquierColor;
    }

    public void setCualquierColor(boolean cualquierColor) {
        this.cualquierColor = cualquierColor;
    }
}