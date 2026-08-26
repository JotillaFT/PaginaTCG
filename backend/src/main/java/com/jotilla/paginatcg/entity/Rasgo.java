package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Rasgo reutilizable de una sección de carta.
 *
 * <p>Se corresponde con la tabla MySQL {@code rasgo}. Representa valores como
 * {@code Holy Warrior} o {@code Royal Knight}, que pueden aparecer en muchas secciones.
 * La relación con las secciones se guarda en {@link SeccionCartaRasgo} para
 * permitir múltiples rasgos y conservar su orden oficial.</p>
 *
 * <p>Se usará para filtros por uno o varios rasgos sin duplicar texto ni usar
 * columnas numeradas.</p>
 */
@Entity
@Table(name = "rasgo")
public class Rasgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre oficial del rasgo tal como se importa del catálogo.
     */
    @Column(name = "nombre", nullable = false, unique = true, length = 255)
    private String nombre;

    protected Rasgo() {
    }

    public Rasgo(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
