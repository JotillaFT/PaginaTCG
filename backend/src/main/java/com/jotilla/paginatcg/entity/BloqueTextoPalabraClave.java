package com.jotilla.paginatcg.entity;

import jakarta.persistence.*;

/**
 * Relación entre un bloque de texto y una palabra clave propia de su sección.
 *
 * <p>Se corresponde con la tabla MySQL {@code bloque_texto_palabra_clave}. La
 * relación sirve como índice estructurado para filtros por palabras clave
 * presentadas directamente por el bloque. No se crea para palabras clave solo
 * mencionadas, concedidas mediante otro efecto, eliminadas, negadas o usadas
 * como condición; esos casos se localizarán mediante búsqueda sobre
 * {@code contenido_oficial}, incluso si la concesión afecta a la propia
 * sección.</p>
 *
 * <p>La fila indica presencia dentro del {@link BloqueTexto} completo, sin
 * dividir el texto oficial en efectos individuales.</p>
 */
@Entity
@Table(
        name = "bloque_texto_palabra_clave",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_bloque_texto_palabra_clave",
                        columnNames = {"bloque_texto_id", "palabra_clave_id"}
                )
        }
)
public class BloqueTextoPalabraClave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bloque_texto_id", nullable = false)
    private BloqueTexto bloqueTexto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "palabra_clave_id", nullable = false)
    private PalabraClave palabraClave;

    protected BloqueTextoPalabraClave() {
    }

    /**
     * Crea la relación entre un bloque completo y una palabra clave propia
     * presentada directamente en él.
     */
    public BloqueTextoPalabraClave(
            BloqueTexto bloqueTexto,
            PalabraClave palabraClave
    ) {
        this.bloqueTexto = bloqueTexto;
        this.palabraClave = palabraClave;
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

    public PalabraClave getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(PalabraClave palabraClave) {
        this.palabraClave = palabraClave;
    }
}
