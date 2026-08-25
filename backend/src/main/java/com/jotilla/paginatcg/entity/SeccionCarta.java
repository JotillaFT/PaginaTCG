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

@Entity
@Table(name = "seccion_carta")
public class SeccionCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carta_id", nullable = false)
    private Carta carta;

    @Column(name = "orden", nullable = false)
    private Integer orden = 1;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "categoria_seccion", nullable = false, length = 50)
    private CategoriaCarta categoriaSeccion;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "dp")
    private Integer dp;

    @Column(name = "coste_juego")
    private Integer costeJuego;

    @Column(name = "coste_uso")
    private Integer costeUso;

    @Column(name = "forma", length = 100)
    private String forma;

    @Column(name = "atributo", length = 100)
    private String atributo;

    protected SeccionCarta() {
    }

    public SeccionCarta(
            Carta carta,
            Integer orden,
            CategoriaCarta categoriaSeccion,
            String nombre
    ) {
        this.carta = carta;
        this.orden = orden;
        this.categoriaSeccion = categoriaSeccion;
        this.nombre = nombre;
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public CategoriaCarta getCategoriaSeccion() {
        return categoriaSeccion;
    }

    public void setCategoriaSeccion(CategoriaCarta categoriaSeccion) {
        this.categoriaSeccion = categoriaSeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getDp() {
        return dp;
    }

    public void setDp(Integer dp) {
        this.dp = dp;
    }

    public Integer getCosteJuego() {
        return costeJuego;
    }

    public void setCosteJuego(Integer costeJuego) {
        this.costeJuego = costeJuego;
    }

    public Integer getCosteUso() {
        return costeUso;
    }

    public void setCosteUso(Integer costeUso) {
        this.costeUso = costeUso;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
}