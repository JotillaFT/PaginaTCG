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
 * Corrección oficial histórica asociada a una carta funcional.
 *
 * <p>Se corresponde con la tabla MySQL {@code errata_carta}. Una errata indica
 * que el texto oficial jugable de una carta cambió o fue aclarado respecto a
 * una impresión previa. El texto funcional mostrado por la aplicación debe ser
 * el texto corregido; esta entidad conserva el cambio para explicar la
 * diferencia al usuario.</p>
 *
 * <p>Las impresiones físicas que contienen el error se relacionan mediante
 * {@link ErrataImpresionCarta}. Una reimpresión posterior corregida puede
 * compartir el historial de errata de la carta sin quedar marcada como
 * impresión errónea.</p>
 */
@Entity
@Table(name = "errata_carta")
public class ErrataCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Carta funcional afectada por la corrección oficial.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carta_id", nullable = false)
    private Carta carta;

    /**
     * Sección concreta afectada cuando pueda identificarse sin inventar datos.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seccion_carta_id")
    private SeccionCarta seccionCarta;

    /**
     * Bloque de texto afectado cuando la errata apunte a una caja oficial
     * concreta.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloque_texto_id")
    private BloqueTexto bloqueTexto;

    /**
     * Fecha oficial de la errata o de su publicación efectiva.
     */
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    /**
     * Orden de la errata dentro de la misma carta y fecha.
     */
    @Column(name = "orden", nullable = false)
    private Integer orden;

    /**
     * Descripción oficial o importada de la parte corregida, por ejemplo
     * {@code Effect}, {@code Security Effect} o {@code Name}.
     */
    @Column(name = "ubicacion", length = 100)
    private String ubicacion;

    /**
     * Texto incorrecto publicado antes de la corrección.
     */
    @Column(
            name = "texto_error",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String textoError;

    /**
     * Texto oficial corregido que debe prevalecer al mostrar y jugar la carta.
     */
    @Column(
            name = "texto_correccion",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String textoCorreccion;

    /**
     * Notas oficiales o explicativas asociadas a la errata.
     */
    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    /**
     * URL de la fuente oficial o de la fuente de importación cuando exista.
     */
    @Column(name = "url_fuente", length = 2048)
    private String urlFuente;

    protected ErrataCarta() {
    }

    public ErrataCarta(
            Carta carta,
            LocalDate fecha,
            Integer orden,
            String textoError,
            String textoCorreccion
    ) {
        this.carta = carta;
        this.fecha = fecha;
        this.orden = orden;
        this.textoError = textoError;
        this.textoCorreccion = textoCorreccion;
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

    public SeccionCarta getSeccionCarta() {
        return seccionCarta;
    }

    public void setSeccionCarta(SeccionCarta seccionCarta) {
        this.seccionCarta = seccionCarta;
    }

    public BloqueTexto getBloqueTexto() {
        return bloqueTexto;
    }

    public void setBloqueTexto(BloqueTexto bloqueTexto) {
        this.bloqueTexto = bloqueTexto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTextoError() {
        return textoError;
    }

    public void setTextoError(String textoError) {
        this.textoError = textoError;
    }

    public String getTextoCorreccion() {
        return textoCorreccion;
    }

    public void setTextoCorreccion(String textoCorreccion) {
        this.textoCorreccion = textoCorreccion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getUrlFuente() {
        return urlFuente;
    }

    public void setUrlFuente(String urlFuente) {
        this.urlFuente = urlFuente;
    }
}
