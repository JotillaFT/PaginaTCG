CREATE TABLE informacion_link (
                                  id BIGINT NOT NULL AUTO_INCREMENT,
                                  seccion_carta_id BIGINT NOT NULL,
                                  bonificacion_dp INT NOT NULL,
                                  contenido_dp_oficial VARCHAR(100) NOT NULL,

                                  PRIMARY KEY (id),

                                  CONSTRAINT uk_informacion_link_seccion
                                      UNIQUE (seccion_carta_id),

                                  CONSTRAINT fk_informacion_link_seccion
                                      FOREIGN KEY (seccion_carta_id)
                                          REFERENCES seccion_carta (id)
                                          ON DELETE CASCADE,

                                  CONSTRAINT chk_informacion_link_bonificacion_dp
                                      CHECK (bonificacion_dp >= 0),

                                  CONSTRAINT chk_informacion_link_contenido_dp_no_vacio
                                      CHECK (
                                          CHAR_LENGTH(TRIM(contenido_dp_oficial)) > 0
                                          )
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE requisito_link (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                informacion_link_id BIGINT NOT NULL,
                                orden INT NOT NULL,
                                coste INT NOT NULL,
                                contenido_oficial TEXT NOT NULL,

                                PRIMARY KEY (id),

                                CONSTRAINT uk_requisito_link_informacion_orden
                                    UNIQUE (informacion_link_id, orden),

                                CONSTRAINT fk_requisito_link_informacion
                                    FOREIGN KEY (informacion_link_id)
                                        REFERENCES informacion_link (id)
                                        ON DELETE CASCADE,

                                CONSTRAINT chk_requisito_link_orden
                                    CHECK (orden > 0),

                                CONSTRAINT chk_requisito_link_coste
                                    CHECK (coste >= 0),

                                CONSTRAINT chk_requisito_link_contenido_no_vacio
                                    CHECK (
                                        CHAR_LENGTH(TRIM(contenido_oficial)) > 0
                                        )
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE requisito_link_rasgo (
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      requisito_link_id BIGINT NOT NULL,
                                      rasgo_id BIGINT NOT NULL,
                                      orden INT NOT NULL,

                                      PRIMARY KEY (id),

                                      CONSTRAINT uk_requisito_link_rasgo_requisito_rasgo
                                          UNIQUE (requisito_link_id, rasgo_id),

                                      CONSTRAINT uk_requisito_link_rasgo_requisito_orden
                                          UNIQUE (requisito_link_id, orden),

                                      CONSTRAINT fk_requisito_link_rasgo_requisito
                                          FOREIGN KEY (requisito_link_id)
                                              REFERENCES requisito_link (id)
                                              ON DELETE CASCADE,

                                      CONSTRAINT fk_requisito_link_rasgo_rasgo
                                          FOREIGN KEY (rasgo_id)
                                              REFERENCES rasgo (id)
                                              ON DELETE RESTRICT,

                                      CONSTRAINT chk_requisito_link_rasgo_orden
                                          CHECK (orden > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;