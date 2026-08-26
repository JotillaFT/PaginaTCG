package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

/**
 * Relación entre un bloque de texto y una etiqueta de efecto presente en él.
 *
 * <p>Se corresponde con la tabla MySQL {@code bloque_texto_etiqueta}. La fila
 * no representa una frase concreta ni una ocurrencia contada; solo indica que
 * la etiqueta existe al menos una vez dentro del {@link BloqueTexto} completo.</p>
 *
 * <p>No almacena la posición exacta ni el número de apariciones. Se usará para
 * filtros que combinen categoría de bloque y etiquetas oficiales de activación
 * o temporización, manteniendo {@code contenido_oficial} como fuente de verdad.</p>
 */
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

    /**
     * Crea la marca de presencia de una etiqueta dentro de un bloque completo.
     */
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
