CREATE TABLE palabra_clave (
                               id BIGINT NOT NULL AUTO_INCREMENT,
                               codigo VARCHAR(100) NOT NULL,
                               nombre_oficial VARCHAR(100) NOT NULL,

                               PRIMARY KEY (id),

                               CONSTRAINT uk_palabra_clave_codigo
                                   UNIQUE (codigo),

                               CONSTRAINT uk_palabra_clave_nombre
                                   UNIQUE (nombre_oficial),

                               CONSTRAINT chk_palabra_clave_codigo
                                   CHECK (CHAR_LENGTH(TRIM(codigo)) > 0),

                               CONSTRAINT chk_palabra_clave_nombre
                                   CHECK (CHAR_LENGTH(TRIM(nombre_oficial)) > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE bloque_texto_palabra_clave (
                                            id BIGINT NOT NULL AUTO_INCREMENT,
                                            bloque_texto_id BIGINT NOT NULL,
                                            palabra_clave_id BIGINT NOT NULL,

                                            PRIMARY KEY (id),

                                            CONSTRAINT uk_bloque_texto_palabra_clave
                                                UNIQUE (bloque_texto_id, palabra_clave_id),

                                            CONSTRAINT fk_bloque_palabra_clave_bloque
                                                FOREIGN KEY (bloque_texto_id)
                                                    REFERENCES bloque_texto (id)
                                                    ON DELETE CASCADE,

                                            CONSTRAINT fk_bloque_palabra_clave_palabra
                                                FOREIGN KEY (palabra_clave_id)
                                                    REFERENCES palabra_clave (id)
                                                    ON DELETE RESTRICT,

                                            INDEX idx_bloque_palabra_clave_palabra
                                                (palabra_clave_id, bloque_texto_id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;