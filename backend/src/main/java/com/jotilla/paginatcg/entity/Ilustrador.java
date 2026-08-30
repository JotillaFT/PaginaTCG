package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Nombre artístico acreditado en una o varias impresiones de cartas.
 */
@Entity
@Table(name = "ilustrador")
public class Ilustrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre conservado tal como aparece en el crédito de la ilustración.
     */
    @Column(
            name = "nombre_credito",
            nullable = false,
            unique = true,
            length = 255
    )
    private String nombreCredito;

    protected Ilustrador() {
    }

    public Ilustrador(String nombreCredito) {
        this.nombreCredito = nombreCredito;
    }

    public Long getId() {
        return id;
    }

    public String getNombreCredito() {
        return nombreCredito;
    }

    public void setNombreCredito(String nombreCredito) {
        this.nombreCredito = nombreCredito;
    }
}