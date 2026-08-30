CREATE TABLE forma_carta (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             codigo VARCHAR(50) NOT NULL,
                             nombre_oficial VARCHAR(100) NOT NULL,

                             PRIMARY KEY (id),

                             CONSTRAINT uk_forma_carta_codigo
                                 UNIQUE (codigo),

                             CONSTRAINT uk_forma_carta_nombre_oficial
                                 UNIQUE (nombre_oficial),

                             CONSTRAINT chk_forma_carta_codigo_no_vacio
                                 CHECK (TRIM(codigo) <> ''),

                             CONSTRAINT chk_forma_carta_nombre_oficial_no_vacio
                                 CHECK (TRIM(nombre_oficial) <> '')
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO forma_carta (codigo, nombre_oficial)
VALUES
    ('IN_TRAINING', 'In-Training'),
    ('ROOKIE', 'Rookie'),
    ('CHAMPION', 'Champion'),
    ('ULTIMATE', 'Ultimate'),
    ('MEGA', 'Mega'),
    ('ARMOR_FORM', 'Armor Form'),
    ('HYBRID', 'Hybrid'),
    ('D_REAPER', 'D-Reaper'),
    ('EATER', 'Eater'),
    ('UNKNOWN', 'Unknown'),
    ('APPMON', 'Appmon'),
    ('STANDARD_APPMON', 'Stnd./Appmon'),
    ('SUPER_APPMON', 'Sup./Appmon'),
    ('ULTIMATE_APPMON', 'Ult./Appmon'),
    ('GOD_APPMON', 'God/Appmon'),
    ('UNKNOWN_APPMON', 'Unknown/Appmon');

ALTER TABLE seccion_carta
    ADD COLUMN forma_carta_id BIGINT NULL AFTER coste_uso,

    ADD CONSTRAINT fk_seccion_carta_forma_carta
        FOREIGN KEY (forma_carta_id)
            REFERENCES forma_carta (id)
            ON DELETE RESTRICT;

UPDATE seccion_carta AS sc
    INNER JOIN forma_carta AS fc
ON fc.nombre_oficial = TRIM(sc.forma)
    SET sc.forma_carta_id = fc.id
WHERE sc.forma IS NOT NULL
  AND TRIM(sc.forma) <> '';


ALTER TABLE seccion_carta
    ADD CONSTRAINT chk_seccion_carta_forma_migrada
        CHECK (
            forma IS NULL
                OR TRIM(forma) = ''
                OR forma_carta_id IS NOT NULL
            );


ALTER TABLE seccion_carta
DROP CHECK chk_seccion_carta_forma_migrada;


ALTER TABLE seccion_carta
DROP COLUMN forma;


CREATE TABLE requisito_evolucion_normal (
                                            id BIGINT NOT NULL AUTO_INCREMENT,
                                            seccion_carta_id BIGINT NOT NULL,
                                            orden INT NOT NULL,

                                            categoria_origen VARCHAR(50) NULL,
                                            nivel_origen INT NULL,
                                            forma_origen_id BIGINT NULL,

                                            coste INT NOT NULL,
                                            cualquier_color BOOLEAN NOT NULL DEFAULT FALSE,

                                            PRIMARY KEY (id),

                                            CONSTRAINT uk_requisito_evolucion_normal_seccion_orden
                                                UNIQUE (seccion_carta_id, orden),

                                            CONSTRAINT fk_requisito_evolucion_normal_seccion
                                                FOREIGN KEY (seccion_carta_id)
                                                    REFERENCES seccion_carta (id)
                                                    ON DELETE CASCADE,

                                            CONSTRAINT fk_requisito_evolucion_normal_forma_origen
                                                FOREIGN KEY (forma_origen_id)
                                                    REFERENCES forma_carta (id)
                                                    ON DELETE RESTRICT,

                                            CONSTRAINT chk_requisito_evolucion_normal_orden
                                                CHECK (orden > 0),

                                            CONSTRAINT chk_requisito_evolucion_normal_nivel_origen
                                                CHECK (nivel_origen IS NULL OR nivel_origen > 0),

                                            CONSTRAINT chk_requisito_evolucion_normal_coste
                                                CHECK (coste >= 0),

                                            CONSTRAINT chk_requisito_evolucion_normal_cualquier_color
                                                CHECK (cualquier_color IN (FALSE, TRUE))
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE requisito_evolucion_normal_color (
                                                  id BIGINT NOT NULL AUTO_INCREMENT,
                                                  requisito_evolucion_normal_id BIGINT NOT NULL,
                                                  color_id BIGINT NOT NULL,
                                                  orden INT NOT NULL,

                                                  PRIMARY KEY (id),

                                                  CONSTRAINT uk_requisito_evolucion_normal_color_requisito_color
                                                      UNIQUE (requisito_evolucion_normal_id, color_id),

                                                  CONSTRAINT uk_requisito_evolucion_normal_color_requisito_orden
                                                      UNIQUE (requisito_evolucion_normal_id, orden),

                                                  CONSTRAINT fk_requisito_evolucion_normal_color_requisito
                                                      FOREIGN KEY (requisito_evolucion_normal_id)
                                                          REFERENCES requisito_evolucion_normal (id)
                                                          ON DELETE CASCADE,

                                                  CONSTRAINT fk_requisito_evolucion_normal_color_color
                                                      FOREIGN KEY (color_id)
                                                          REFERENCES color (id)
                                                          ON DELETE RESTRICT,

                                                  CONSTRAINT chk_requisito_evolucion_normal_color_orden
                                                      CHECK (orden > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
