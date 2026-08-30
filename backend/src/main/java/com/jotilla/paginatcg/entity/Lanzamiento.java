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

import java.time.LocalDate;

/**
 * Producto, promoción, torneo u otro lanzamiento que contiene impresiones.
 */
@Entity
@Table(name = "lanzamiento")
public class Lanzamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    /**
     * Código estable del lanzamiento dentro de su idioma, por ejemplo
     * {@code bt-05}.
     */
    @Column(name = "codigo", nullable = false, length = 150)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "genero", length = 100)
    private String genero;

    /**
     * Fecha oficial cuando se conoce. Algunos grupos de promociones o eventos
     * no tienen una única fecha y conservan este campo en {@code null}.
     */
    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "url_producto", length = 2048)
    private String urlProducto;

    @Column(name = "url_lista_cartas", length = 2048)
    private String urlListaCartas;

    @Column(name = "url_imagen", length = 2048)
    private String urlImagen;

    @Column(name = "url_miniatura", length = 2048)
    private String urlMiniatura;

    protected Lanzamiento() {
    }

    public Lanzamiento(Idioma idioma, String codigo, String nombre) {
        this.idioma = idioma;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUrlProducto() {
        return urlProducto;
    }

    public void setUrlProducto(String urlProducto) {
        this.urlProducto = urlProducto;
    }

    public String getUrlListaCartas() {
        return urlListaCartas;
    }

    public void setUrlListaCartas(String urlListaCartas) {
        this.urlListaCartas = urlListaCartas;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUrlMiniatura() {
        return urlMiniatura;
    }

    public void setUrlMiniatura(String urlMiniatura) {
        this.urlMiniatura = urlMiniatura;
    }
}