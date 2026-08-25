package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "bloque_texto_etiqueta",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_bloque_texto_etiqueta",
                        columnNames = {"bloque_texto_id", "etiqueta_efecto_id"}
                )
        }
)
public class BloqueTextoEtiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bloque_texto_id", nullable = false)
    private BloqueTexto bloqueTexto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "etiqueta_efecto_id", nullable = false)
    private EtiquetaEfecto etiquetaEfecto;

    protected BloqueTextoEtiqueta() {
    }

    public BloqueTextoEtiqueta(
            BloqueTexto bloqueTexto,
            EtiquetaEfecto etiquetaEfecto
    ) {
        this.bloqueTexto = bloqueTexto;
        this.etiquetaEfecto = etiquetaEfecto;
    }

    public Long getId() {
        return id;
    }

    public BloqueTexto getBloqueTexto() {
        return bloqueTexto;
    }

    public void setBloqueTexto(BloqueTexto bloqueTexto) {
        this.bloqueTexto = bloqueTexto;
    }

    public EtiquetaEfecto getEtiquetaEfecto() {
        return etiquetaEfecto;
    }

    public void setEtiquetaEfecto(EtiquetaEfecto etiquetaEfecto) {
        this.etiquetaEfecto = etiquetaEfecto;
    }
}