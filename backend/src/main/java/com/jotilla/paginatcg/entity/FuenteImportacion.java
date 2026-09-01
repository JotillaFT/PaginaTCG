package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Fuente externa o manual desde la que se obtienen datos para importar.
 *
 * <p>Se corresponde con la tabla MySQL {@code fuente_importacion}. El catálogo
 * permite auditar fuentes como Heroicc sin acoplar los lotes a un proveedor
 * concreto.</p>
 */
@Entity
@Table(
        name = "fuente_importacion",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_fuente_importacion_codigo",
                        columnNames = "codigo"
                ),
                @UniqueConstraint(
                        name = "uk_fuente_importacion_nombre",
                        columnNames = "nombre"
                )
        }
)
public class FuenteImportacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "url_base", length = 2048)
    private String urlBase;

    protected FuenteImportacion() {
    }

    public FuenteImportacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }
}
