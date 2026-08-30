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
 * Variante física o gráfica de una carta funcional.
 *
 * <p>La variante {@code 0} representa la impresión base dentro de un idioma.
 * Los valores superiores representan artes alternativos o reimpresiones
 * diferenciadas. Todas las variantes reutilizan la misma {@link Carta}.</p>
 */
@Entity
@Table(name = "impresion_carta")
public class ImpresionCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carta_id", nullable = false)
    private Carta carta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    @Column(name = "numero_variante", nullable = false)
    private Integer numeroVariante = 0;

    @Column(name = "url_imagen", length = 2048)
    private String urlImagen;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    /**
     * Número de estrellas de rareza suplementaria cuando exista.
     */
    @Column(name = "estrellas")
    private Integer estrellas;

    /**
     * Sello suplementario, por ejemplo {@code SP}.
     */
    @Column(name = "sello", length = 50)
    private String sello;

    protected ImpresionCarta() {
    }

    public ImpresionCarta(
            Carta carta,
            Idioma idioma,
            Integer numeroVariante
    ) {
        this.carta = carta;
        this.idioma = idioma;
        this.numeroVariante = numeroVariante;
    }

    public Long getId() {
        return id;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroVariante() {
        return numeroVariante;
    }

    public void setNumeroVariante(Integer numeroVariante) {
        this.numeroVariante = numeroVariante;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }
}