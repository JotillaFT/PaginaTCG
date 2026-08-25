CREATE TABLE color (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       codigo VARCHAR(30) NOT NULL,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_color_codigo
                           UNIQUE (codigo)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE seccion_carta_color (
                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                     seccion_carta_id BIGINT NOT NULL,
                                     color_id BIGINT NOT NULL,
                                     orden INT NOT NULL,

                                     PRIMARY KEY (id),

                                     CONSTRAINT uk_seccion_carta_color_seccion_color
                                         UNIQUE (seccion_carta_id, color_id),

                                     CONSTRAINT uk_seccion_carta_color_seccion_orden
                                         UNIQUE (seccion_carta_id, orden),

                                     CONSTRAINT fk_seccion_carta_color_seccion
                                         FOREIGN KEY (seccion_carta_id)
                                             REFERENCES seccion_carta (id)
                                             ON DELETE CASCADE,

                                     CONSTRAINT fk_seccion_carta_color_color
                                         FOREIGN KEY (color_id)
                                             REFERENCES color (id)
                                             ON DELETE RESTRICT,

                                     CONSTRAINT chk_seccion_carta_color_orden
                                         CHECK (orden > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO color (codigo)
VALUES
    ('RED'),
    ('BLUE'),
    ('YELLOW'),
    ('GREEN'),
    ('BLACK'),
    ('PURPLE'),
    ('WHITE');