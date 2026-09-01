CREATE TABLE errata_carta (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              carta_id BIGINT NOT NULL,
                              seccion_carta_id BIGINT NULL,
                              bloque_texto_id BIGINT NULL,
                              fecha DATE NOT NULL,
                              orden INT NOT NULL,
                              ubicacion VARCHAR(100) NULL,
                              texto_error TEXT NOT NULL,
                              texto_correccion TEXT NOT NULL,
                              notas TEXT NULL,
                              url_fuente VARCHAR(2048) NULL,

                              CONSTRAINT pk_errata_carta
                                  PRIMARY KEY (id),

                              CONSTRAINT uk_errata_carta_fecha_orden
                                  UNIQUE (carta_id, fecha, orden),

                              CONSTRAINT fk_errata_carta_carta
                                  FOREIGN KEY (carta_id)
                                      REFERENCES carta (id)
                                      ON DELETE CASCADE,

                              CONSTRAINT fk_errata_carta_seccion
                                  FOREIGN KEY (seccion_carta_id)
                                      REFERENCES seccion_carta (id)
                                      ON DELETE SET NULL,

                              CONSTRAINT fk_errata_carta_bloque
                                  FOREIGN KEY (bloque_texto_id)
                                      REFERENCES bloque_texto (id)
                                      ON DELETE SET NULL,

                              CONSTRAINT chk_errata_carta_orden
                                  CHECK (orden > 0),

                              CONSTRAINT chk_errata_carta_ubicacion
                                  CHECK (
                                      ubicacion IS NULL
                                          OR CHAR_LENGTH(TRIM(ubicacion)) > 0
                                      ),

                              CONSTRAINT chk_errata_carta_texto_error
                                  CHECK (CHAR_LENGTH(TRIM(texto_error)) > 0),

                              CONSTRAINT chk_errata_carta_texto_correccion
                                  CHECK (CHAR_LENGTH(TRIM(texto_correccion)) > 0),

                              CONSTRAINT chk_errata_carta_url_fuente
                                  CHECK (
                                      url_fuente IS NULL
                                          OR CHAR_LENGTH(TRIM(url_fuente)) > 0
                                      ),

                              INDEX idx_errata_carta_carta_fecha
                                  (carta_id, fecha),

                              INDEX idx_errata_carta_seccion
                                  (seccion_carta_id),

                              INDEX idx_errata_carta_bloque
                                  (bloque_texto_id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE errata_impresion_carta (
                                        id BIGINT NOT NULL AUTO_INCREMENT,
                                        errata_carta_id BIGINT NOT NULL,
                                        impresion_carta_id BIGINT NOT NULL,
                                        notas TEXT NULL,

                                        CONSTRAINT pk_errata_impresion_carta
                                            PRIMARY KEY (id),

                                        CONSTRAINT uk_errata_impresion_carta_errata_impresion
                                            UNIQUE (errata_carta_id, impresion_carta_id),

                                        CONSTRAINT fk_errata_impresion_carta_errata
                                            FOREIGN KEY (errata_carta_id)
                                                REFERENCES errata_carta (id)
                                                ON DELETE CASCADE,

                                        CONSTRAINT fk_errata_impresion_carta_impresion
                                            FOREIGN KEY (impresion_carta_id)
                                                REFERENCES impresion_carta (id)
                                                ON DELETE CASCADE,

                                        INDEX idx_errata_impresion_carta_impresion
                                            (impresion_carta_id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
