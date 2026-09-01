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
import java.time.LocalDateTime;

/**
 * Ejecución concreta de importación o análisis de datos externos.
 *
 * <p>Se corresponde con la tabla MySQL {@code lote_importacion}. Conserva la
 * procedencia, las fechas, el estado y los contadores necesarios para auditar
 * una ejecución sin implementar por sí misma la lógica del importador.</p>
 */
@Entity
@Table(name = "lote_importacion")
public class LoteImportacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fuente_importacion_id", nullable = false)
    private FuenteImportacion fuenteImportacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idioma_id")
    private Idioma idioma;

    @Column(name = "tipo_datos", nullable = false, length = 100)
    private String tipoDatos;

    @Column(name = "identificador_fuente", nullable = false, length = 255)
    private String identificadorFuente;

    @Column(name = "url_fuente", length = 2048)
    private String urlFuente;

    @Column(name = "hash_sha256", length = 64, columnDefinition = "CHAR(64)")
    private String hashSha256;

    @Column(name = "fecha_datos")
    private LocalDate fechaDatos;

    @Column(name = "iniciado_en", nullable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime iniciadoEn;

    @Column(name = "finalizado_en", columnDefinition = "DATETIME(6)")
    private LocalDateTime finalizadoEn;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "total_registros_fuente")
    private Integer totalRegistrosFuente;

    @Column(name = "total_registros_procesados", nullable = false)
    private Integer totalRegistrosProcesados = 0;

    @Column(name = "total_inserciones", nullable = false)
    private Integer totalInserciones = 0;

    @Column(name = "total_actualizaciones", nullable = false)
    private Integer totalActualizaciones = 0;

    @Column(name = "total_omisiones", nullable = false)
    private Integer totalOmisiones = 0;

    @Column(name = "total_advertencias", nullable = false)
    private Integer totalAdvertencias = 0;

    @Column(name = "total_errores", nullable = false)
    private Integer totalErrores = 0;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    protected LoteImportacion() {
    }

    public LoteImportacion(
            FuenteImportacion fuenteImportacion,
            String tipoDatos,
            String identificadorFuente,
            LocalDateTime iniciadoEn,
            String estado
    ) {
        this.fuenteImportacion = fuenteImportacion;
        this.tipoDatos = tipoDatos;
        this.identificadorFuente = identificadorFuente;
        this.iniciadoEn = iniciadoEn;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public FuenteImportacion getFuenteImportacion() {
        return fuenteImportacion;
    }

    public void setFuenteImportacion(FuenteImportacion fuenteImportacion) {
        this.fuenteImportacion = fuenteImportacion;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getTipoDatos() {
        return tipoDatos;
    }

    public void setTipoDatos(String tipoDatos) {
        this.tipoDatos = tipoDatos;
    }

    public String getIdentificadorFuente() {
        return identificadorFuente;
    }

    public void setIdentificadorFuente(String identificadorFuente) {
        this.identificadorFuente = identificadorFuente;
    }

    public String getUrlFuente() {
        return urlFuente;
    }

    public void setUrlFuente(String urlFuente) {
        this.urlFuente = urlFuente;
    }

    public String getHashSha256() {
        return hashSha256;
    }

    public void setHashSha256(String hashSha256) {
        this.hashSha256 = hashSha256;
    }

    public LocalDate getFechaDatos() {
        return fechaDatos;
    }

    public void setFechaDatos(LocalDate fechaDatos) {
        this.fechaDatos = fechaDatos;
    }

    public LocalDateTime getIniciadoEn() {
        return iniciadoEn;
    }

    public void setIniciadoEn(LocalDateTime iniciadoEn) {
        this.iniciadoEn = iniciadoEn;
    }

    public LocalDateTime getFinalizadoEn() {
        return finalizadoEn;
    }

    public void setFinalizadoEn(LocalDateTime finalizadoEn) {
        this.finalizadoEn = finalizadoEn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getTotalRegistrosFuente() {
        return totalRegistrosFuente;
    }

    public void setTotalRegistrosFuente(Integer totalRegistrosFuente) {
        this.totalRegistrosFuente = totalRegistrosFuente;
    }

    public Integer getTotalRegistrosProcesados() {
        return totalRegistrosProcesados;
    }

    public void setTotalRegistrosProcesados(Integer totalRegistrosProcesados) {
        this.totalRegistrosProcesados = totalRegistrosProcesados;
    }

    public Integer getTotalInserciones() {
        return totalInserciones;
    }

    public void setTotalInserciones(Integer totalInserciones) {
        this.totalInserciones = totalInserciones;
    }

    public Integer getTotalActualizaciones() {
        return totalActualizaciones;
    }

    public void setTotalActualizaciones(Integer totalActualizaciones) {
        this.totalActualizaciones = totalActualizaciones;
    }

    public Integer getTotalOmisiones() {
        return totalOmisiones;
    }

    public void setTotalOmisiones(Integer totalOmisiones) {
        this.totalOmisiones = totalOmisiones;
    }

    public Integer getTotalAdvertencias() {
        return totalAdvertencias;
    }

    public void setTotalAdvertencias(Integer totalAdvertencias) {
        this.totalAdvertencias = totalAdvertencias;
    }

    public Integer getTotalErrores() {
        return totalErrores;
    }

    public void setTotalErrores(Integer totalErrores) {
        this.totalErrores = totalErrores;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
