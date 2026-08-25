CREATE TABLE bloque_texto (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              seccion_carta_id BIGINT NOT NULL,
                              categoria_bloque VARCHAR(50) NOT NULL,
                              orden INT NOT NULL,
                              contenido_oficial TEXT NOT NULL,

                              PRIMARY KEY (id),

                              CONSTRAINT uk_bloque_texto_seccion_orden
                                  UNIQUE (seccion_carta_id, orden),

                              CONSTRAINT fk_bloque_texto_seccion
                                  FOREIGN KEY (seccion_carta_id)
                                      REFERENCES seccion_carta (id)
                                      ON DELETE CASCADE,

                              CONSTRAINT chk_bloque_texto_orden
                                  CHECK (orden > 0),

                              CONSTRAINT chk_bloque_texto_contenido
                                  CHECK (CHAR_LENGTH(TRIM(contenido_oficial)) > 0),

                              INDEX idx_bloque_texto_seccion_categoria
                                  (seccion_carta_id, categoria_bloque)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;