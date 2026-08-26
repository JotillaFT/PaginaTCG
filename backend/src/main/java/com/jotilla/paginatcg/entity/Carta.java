package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * Identidad lógica de una carta del Digimon Card Game.
 *
 * <p>Se corresponde con la tabla MySQL {@code carta}. Esta entidad guarda los
 * datos comunes de la carta independientemente de futuras impresiones, artes
 * alternativas o productos. Sus secciones funcionales se modelan aparte en
 * {@link SeccionCarta}, de modo que una carta normal pueda tener una sección y
 * una carta {@link CategoriaCarta#DUAL} pueda tener varias.</p>
 *
 * <p>Se usará como raíz para filtros por código oficial, nombre, categoría,
 * rareza, icono de bloque y límite de copias.</p>
 */
@Entity
@Table(name = "carta")
public class Carta {
    /**
     * Identificador interno generado por MySQL. No es el número oficial visible
     * de la carta; para eso se usa {@link #codigo}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Número visible y oficial de la carta, por ejemplo {@code BT5-086}.
     */
    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(name = "nombre_general", nullable = false, length = 255)
    private String nombreGeneral;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "categoria_carta", nullable = false, length = 50)
    private CategoriaCarta categoriaCarta;

    @Column(name = "rareza_base", length = 20)
    private String rarezaBase;

    @Column(name = "icono_bloque", length = 10)
    private String iconoBloque;

    /**
     * Límite propio de construcción asociado a la carta. Por defecto es 4, que
     * representa el límite habitual; restricciones externas, baneos y pares
     * prohibidos se modelarán aparte.
     */
    @Column(name = "limite_copias_regla", nullable = false)
    private Integer limiteCopiasRegla = 4;

    protected Carta() {
    }

    public Carta(
            String codigo,
            String nombreGeneral,
            CategoriaCarta categoriaCarta
    ) {
        this.codigo = codigo;
        this.nombreGeneral = nombreGeneral;
        this.categoriaCarta = categoriaCarta;
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

    public String getNombreGeneral() {
        return nombreGeneral;
    }

    public void setNombreGeneral(String nombreGeneral) {
        this.nombreGeneral = nombreGeneral;
    }

    public CategoriaCarta getCategoriaCarta() {
        return categoriaCarta;
    }

    public void setCategoriaCarta(CategoriaCarta categoriaCarta) {
        this.categoriaCarta = categoriaCarta;
    }

    public String getRarezaBase() {
        return rarezaBase;
    }

    public void setRarezaBase(String rarezaBase) {
        this.rarezaBase = rarezaBase;
    }

    public String getIconoBloque() {
        return iconoBloque;
    }

    public void setIconoBloque(String iconoBloque) {
        this.iconoBloque = iconoBloque;
    }

    public Integer getLimiteCopiasRegla() {
        return limiteCopiasRegla;
    }

    public void setLimiteCopiasRegla(Integer limiteCopiasRegla) {
        this.limiteCopiasRegla = limiteCopiasRegla;
    }
}
