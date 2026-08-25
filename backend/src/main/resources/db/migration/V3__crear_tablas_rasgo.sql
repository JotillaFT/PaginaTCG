CREATE TABLE rasgo (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       nombre VARCHAR(255) NOT NULL,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_rasgo_nombre
                           UNIQUE (nombre)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE seccion_carta_rasgo (
                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                     seccion_carta_id BIGINT NOT NULL,
                                     rasgo_id BIGINT NOT NULL,
                                     orden INT NOT NULL,

                                     PRIMARY KEY (id),

                                     CONSTRAINT uk_seccion_carta_rasgo_seccion_rasgo
                                         UNIQUE (seccion_carta_id, rasgo_id),

                                     CONSTRAINT uk_seccion_carta_rasgo_seccion_orden
                                         UNIQUE (seccion_carta_id, orden),

                                     CONSTRAINT fk_seccion_carta_rasgo_seccion
                                         FOREIGN KEY (seccion_carta_id)
                                             REFERENCES seccion_carta (id)
                                             ON DELETE CASCADE,

                                     CONSTRAINT fk_seccion_carta_rasgo_rasgo
                                         FOREIGN KEY (rasgo_id)
                                             REFERENCES rasgo (id)
                                             ON DELETE RESTRICT,

                                     CONSTRAINT chk_seccion_carta_rasgo_orden
                                         CHECK (orden > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;