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
 * Requisito oficial para utilizar la mecánica Link de una sección.
 *
 * <p>Se corresponde con la tabla MySQL {@code requisito_link}. Cada fila
 * representa una alternativa oficial completa, conserva su coste y mantiene
 * el texto exacto del requisito como fuente de verdad.</p>
 *
 * <p>Los valores oficiales escritos como rasgos admitidos se relacionan
 * mediante {@link RequisitoLinkRasgo}.</p>
 */
@Entity
@Table(name = "requisito_link")
public class RequisitoLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Información Link a la que pertenece este requisito.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "informacion_link_id", nullable = false)
    private InformacionLink informacionLink;

    /**
     * Posición oficial del requisito dentro de la información Link.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    /**
     * Coste oficial necesario para enlazar la carta.
     */
    @Column(name = "coste", nullable = false)
    private Integer coste;

    /**
     * Texto oficial completo del requisito, por ejemplo
     * {@code <Link> [Appmon] trait: Cost 2}.
     *
     * <p>Este contenido se conserva aunque sus componentes filtrables también
     * se representen mediante relaciones estructuradas.</p>
     */
    @Column(
            name = "contenido_oficial",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String contenidoOficial;

    protected RequisitoLink() {
    }

    public RequisitoLink(
            InformacionLink informacionLink,
            Integer orden,
            Integer coste,
            String contenidoOficial
    ) {
        this.informacionLink = informacionLink;
        this.orden = orden;
        this.coste = coste;
        this.contenidoOficial = contenidoOficial;
    }

    public Long getId() {
        return id;
    }

    public InformacionLink getInformacionLink() {
        return informacionLink;
    }

    public void setInformacionLink(InformacionLink informacionLink) {
        this.informacionLink = informacionLink;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getCoste() {
        return coste;
    }

    public void setCoste(Integer coste) {
        this.coste = coste;
    }

    public String getContenidoOficial() {
        return contenidoOficial;
    }

    public void setContenidoOficial(String contenidoOficial) {
        this.contenidoOficial = contenidoOficial;
    }
}