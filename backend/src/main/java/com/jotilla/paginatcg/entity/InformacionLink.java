package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Información estructurada de la mecánica Link de una sección de carta.
 *
 * <p>Se corresponde con la tabla MySQL {@code informacion_link}. Cada fila
 * indica que una sección posee información Link y almacena la bonificación
 * de DP que concede al Digimon enlazado.</p>
 *
 * <p>Los requisitos y costes se modelan mediante {@link RequisitoLink}. Los
 * efectos completos que recibe el Digimon enlazado permanecen en
 * {@link BloqueTexto} con la categoría
 * {@link CategoriaBloqueTexto#LINK_EFFECT}.</p>
 */
@Entity
@Table(name = "informacion_link")
public class InformacionLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Sección que presenta esta información Link.
     *
     * <p>Una sección puede tener como máximo una fila de información Link.</p>
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "seccion_carta_id",
            nullable = false,
            unique = true
    )
    private SeccionCarta seccionCarta;

    /**
     * DP que se añade al Digimon enlazado.
     */
    @Column(name = "bonificacion_dp", nullable = false)
    private Integer bonificacionDp;

    /**
     * Representación oficial del valor de Link DP, por ejemplo
     * {@code Link DP +3000}.
     *
     * <p>No contiene los efectos Link, que se conservan en el
     * {@link BloqueTexto} correspondiente.</p>
     */
    @Column(
            name = "contenido_dp_oficial",
            nullable = false,
            length = 100
    )
    private String contenidoDpOficial;

    protected InformacionLink() {
    }

    public InformacionLink(
            SeccionCarta seccionCarta,
            Integer bonificacionDp,
            String contenidoDpOficial
    ) {
        this.seccionCarta = seccionCarta;
        this.bonificacionDp = bonificacionDp;
        this.contenidoDpOficial = contenidoDpOficial;
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

    public Integer getBonificacionDp() {
        return bonificacionDp;
    }

    public void setBonificacionDp(Integer bonificacionDp) {
        this.bonificacionDp = bonificacionDp;
    }

    public String getContenidoDpOficial() {
        return contenidoDpOficial;
    }

    public void setContenidoDpOficial(String contenidoDpOficial) {
        this.contenidoDpOficial = contenidoDpOficial;
    }
}