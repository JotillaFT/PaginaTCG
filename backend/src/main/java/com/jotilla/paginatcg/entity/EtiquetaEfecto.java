package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "etiqueta_efecto")
public class EtiquetaEfecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String codigo;

    @Column(name = "nombre_oficial", nullable = false, unique = true, length = 100)
    private String nombreOficial;

    protected EtiquetaEfecto() {
    }

    public EtiquetaEfecto(String codigo, String nombreOficial) {
        this.codigo = codigo;
        this.nombreOficial = nombreOficial;
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

    public String getNombreOficial() {
        return nombreOficial;
    }

    public void setNombreOficial(String nombreOficial) {
        this.nombreOficial = nombreOficial;
    }
}