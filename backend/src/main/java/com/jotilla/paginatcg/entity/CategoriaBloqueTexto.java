package com.jotilla.paginatcg.entity;

/**
 * Categoría cerrada de una caja oficial de texto.
 *
 * <p>Se usa en {@link BloqueTexto} para distinguir entre efecto normal, efecto
 * heredado, seguridad, regla y Link. {@link #EFFECT} representa la caja normal
 * de efectos y no se llama {@code MAIN} porque {@code [Main]} es una etiqueta
 * oficial de activación distinta, modelada en el catálogo extensible
 * {@link EtiquetaEfecto}.</p>
 *
 * <p>Este enum se reserva para un conjunto controlado. Catálogos extensibles
 * como etiquetas de efecto y palabras clave se almacenan en tablas.</p>
 */
public enum CategoriaBloqueTexto {
    EFFECT,
    INHERITED_EFFECT,
    SECURITY_EFFECT,
    RULE,
    LINK_EFFECT
}
