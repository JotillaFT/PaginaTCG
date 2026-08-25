CREATE TABLE etiqueta_efecto (
                                 id BIGINT NOT NULL AUTO_INCREMENT,
                                 codigo VARCHAR(100) NOT NULL,
                                 nombre_oficial VARCHAR(100) NOT NULL,

                                 PRIMARY KEY (id),

                                 CONSTRAINT uk_etiqueta_efecto_codigo
                                     UNIQUE (codigo),

                                 CONSTRAINT uk_etiqueta_efecto_nombre
                                     UNIQUE (nombre_oficial),

                                 CONSTRAINT chk_etiqueta_efecto_codigo
                                     CHECK (CHAR_LENGTH(TRIM(codigo)) > 0),

                                 CONSTRAINT chk_etiqueta_efecto_nombre
                                     CHECK (CHAR_LENGTH(TRIM(nombre_oficial)) > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE bloque_texto_etiqueta (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       bloque_texto_id BIGINT NOT NULL,
                                       etiqueta_efecto_id BIGINT NOT NULL,

                                       PRIMARY KEY (id),

                                       CONSTRAINT uk_bloque_texto_etiqueta
                                           UNIQUE (bloque_texto_id, etiqueta_efecto_id),

                                       CONSTRAINT fk_bloque_texto_etiqueta_bloque
                                           FOREIGN KEY (bloque_texto_id)
                                               REFERENCES bloque_texto (id)
                                               ON DELETE CASCADE,

                                       CONSTRAINT fk_bloque_texto_etiqueta_etiqueta
                                           FOREIGN KEY (etiqueta_efecto_id)
                                               REFERENCES etiqueta_efecto (id)
                                               ON DELETE RESTRICT,

                                       INDEX idx_bloque_texto_etiqueta_etiqueta
                                           (etiqueta_efecto_id, bloque_texto_id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;