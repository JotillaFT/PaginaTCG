CREATE TABLE seccion_carta (
                               id BIGINT NOT NULL AUTO_INCREMENT,
                               carta_id BIGINT NOT NULL,
                               orden INT NOT NULL DEFAULT 1,

                               categoria_seccion VARCHAR(50) NOT NULL,
                               nombre VARCHAR(255) NOT NULL,

                               nivel INT NULL,
                               dp INT NULL,
                               coste_juego INT NULL,
                               coste_uso INT NULL,
                               forma VARCHAR(100) NULL,
                               atributo VARCHAR(100) NULL,

                               PRIMARY KEY (id),

                               CONSTRAINT uk_seccion_carta_carta_orden
                                   UNIQUE (carta_id, orden),

                               CONSTRAINT fk_seccion_carta_carta
                                   FOREIGN KEY (carta_id)
                                       REFERENCES carta (id)
                                       ON DELETE CASCADE,

                               CONSTRAINT chk_seccion_carta_orden
                                   CHECK (orden > 0),

                               CONSTRAINT chk_seccion_carta_nivel
                                   CHECK (nivel IS NULL OR nivel >= 0),

                               CONSTRAINT chk_seccion_carta_dp
                                   CHECK (dp IS NULL OR dp >= 0),

                               CONSTRAINT chk_seccion_carta_coste_juego
                                   CHECK (coste_juego IS NULL OR coste_juego >= 0),

                               CONSTRAINT chk_seccion_carta_coste_uso
                                   CHECK (coste_uso IS NULL OR coste_uso >= 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;