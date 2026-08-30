package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Forma oficial de una carta del Digimon Card Game.
 *
 * <p>Se corresponde con la tabla MySQL {@code forma_carta}. Es un catálogo
 * extensible y no un enum porque Bandai puede incorporar nuevas formas o
 * sistemas de evolución en el futuro.</p>
 *
 * <p>Una forma puede describir tanto la forma propia de una
 * {@link SeccionCarta} como la forma exigida por un requisito normal de
 * evolución. Son relaciones diferentes que reutilizan el mismo catálogo
 * oficial.</p>
 */
@Entity
@Table(name = "forma_carta")
public class FormaCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código interno estable utilizado por la aplicación.
     */
    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    /**
     * Nombre oficial publicado por Bandai.
     */
    @Column(name = "nombre_oficial", nullable = false, unique = true, length = 100)
    private String nombreOficial;

    protected FormaCarta() {
    }

    public FormaCarta(String codigo, String nombreOficial) {
        this.codigo = codigo;
        this.nombreOficial = nombreOficial;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreOficial() {
        return nombreOficial;
    }
}