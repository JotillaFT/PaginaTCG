package com.jotilla.paginatcg.entity;

/**
 * Categoría general o funcional de una carta o sección.
 *
 * <p>Se usa como enum porque el conjunto de categorías principales del modelo
 * está controlado. En {@link Carta} clasifica la identidad lógica completa; en
 * {@link SeccionCarta} permite que una carta {@link #DUAL} tenga secciones con
 * categorías concretas.</p>
 *
 * <p>Los catálogos extensibles, como etiquetas de efecto o palabras clave, se
 * almacenan en tablas propias y no como enums.</p>
 */
public enum CategoriaCarta {
    DIGIMON,
    DIGI_EGG,
    TAMER,
    OPTION,
    DUAL,
    TOKEN
}
