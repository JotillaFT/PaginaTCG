package com.jotilla.paginatcg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "carta")
public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(name = "nombre_general", nullable = false, length = 255)
    private String nombreGeneral;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "categoria_carta", nullable = false, length = 50)
    private CategoriaCarta categoriaCarta;

    @Column(name = "rareza_base", length = 20)
    private String rarezaBase;

    @Column(name = "icono_bloque", length = 10)
    private String iconoBloque;

    @Column(name = "limite_copias_regla", nullable = false)
    private Integer limiteCopiasRegla = 4;

    protected Carta() {
    }
}
