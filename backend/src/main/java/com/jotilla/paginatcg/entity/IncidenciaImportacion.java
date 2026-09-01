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

import java.time.LocalDateTime;

/**
 * Información, advertencia o error detectado durante una importación.
 *
 * <p>Se corresponde con la tabla MySQL {@code incidencia_importacion}. Cada
 * incidencia conserva contexto suficiente para revisar el lote sin acoplar la
 * auditoría a una entidad de destino concreta.</p>
 */
@Entity
@Table(name = "incidencia_importacion")
public class IncidenciaImportacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lote_importacion_id", nullable = false)
    private LoteImportacion loteImportacion;

    @Column(name = "severidad", nullable = false, length = 20)
    private String severidad;

    @Column(name = "codigo", nullable = false, length = 100)
    private String codigo;

    @Column(name = "referencia_fuente", length = 255)
    private String referenciaFuente;

    @Column(name = "entidad_destino", length = 100)
    private String entidadDestino;

    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

    @Column(name = "detalle", columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "creado_en", nullable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime creadoEn;

    protected IncidenciaImportacion() {
    }

    public IncidenciaImportacion(
            LoteImportacion loteImportacion,
            String severidad,
            String codigo,
            String mensaje,
            LocalDateTime creadoEn
    ) {
        this.loteImportacion = loteImportacion;
        this.severidad = severidad;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.creadoEn = creadoEn;
    }

    public Long getId() {
        return id;
    }

    public LoteImportacion getLoteImportacion() {
        return loteImportacion;
    }

    public void setLoteImportacion(LoteImportacion loteImportacion) {
        this.loteImportacion = loteImportacion;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferenciaFuente() {
        return referenciaFuente;
    }

    public void setReferenciaFuente(String referenciaFuente) {
        this.referenciaFuente = referenciaFuente;
    }

    public String getEntidadDestino() {
        return entidadDestino;
    }

    public void setEntidadDestino(String entidadDestino) {
        this.entidadDestino = entidadDestino;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}
