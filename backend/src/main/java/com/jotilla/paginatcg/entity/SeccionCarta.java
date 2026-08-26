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
 * Sección funcional de una carta.
 *
 * <p>Se corresponde con la tabla MySQL {@code seccion_carta}. Cada sección
 * pertenece a una {@link Carta}; una carta normal tendrá normalmente una sola
 * sección, mientras que una carta {@link CategoriaCarta#DUAL} puede tener
 * varias secciones con categorías concretas.</p>
 *
 * <p>Es la entidad que concentra los datos filtrables propios de cada parte de
 * la carta: nivel, DP, costes, forma, atributo, colores, rasgos y bloques de
 * texto asociados.</p>
 */
@Entity
@Table(name = "seccion_carta")
public class SeccionCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carta_id", nullable = false)
    private Carta carta;

    /**
     * Posición de la sección dentro de la carta. Permite conservar el orden
     * oficial cuando una misma carta tiene varias secciones.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden = 1;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "categoria_seccion", nullable = false, length = 50)
    private CategoriaCarta categoriaSeccion;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    /**
     * Nivel de la sección cuando existe. Se mantiene en {@code null} para
     * cartas o secciones sin nivel, en lugar de usar valores artificiales.
     */
    @Column(name = "nivel")
    private Integer nivel;

    /**
     * DP de la sección cuando existe. Es {@code null} en categorías donde el
     * dato no aplica.
     */
    @Column(name = "dp")
    private Integer dp;

    /**
     * Coste de juego cuando existe. Es {@code null} si la sección no tiene ese
     * tipo de coste.
     */
    @Column(name = "coste_juego")
    private Integer costeJuego;

    /**
     * Coste de uso cuando existe, especialmente relevante para Options. Es
     * independiente del coste de juego.
     */
    @Column(name = "coste_uso")
    private Integer costeUso;

    /**
     * Forma oficial, por ejemplo {@code Mega}. No debe mezclarse con atributo
     * ni con rasgos.
     */
    @Column(name = "forma", length = 100)
    private String forma;

    /**
     * Atributo oficial, por ejemplo {@code Vaccine}. Los rasgos múltiples se
     * modelan aparte mediante {@link SeccionCartaRasgo}.
     */
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
