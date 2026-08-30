CREATE TABLE tipo_restriccion_competitiva (
                                              id BIGINT NOT NULL AUTO_INCREMENT,
                                              codigo VARCHAR(50) NOT NULL,
                                              nombre_oficial VARCHAR(100) NOT NULL,

                                              CONSTRAINT pk_tipo_restriccion_competitiva
                                                  PRIMARY KEY (id),

                                              CONSTRAINT uk_tipo_restriccion_competitiva_codigo
                                                  UNIQUE (codigo),

                                              CONSTRAINT uk_tipo_restriccion_competitiva_nombre
                                                  UNIQUE (nombre_oficial),

                                              CONSTRAINT chk_tipo_restriccion_competitiva_codigo
                                                  CHECK (TRIM(codigo) <> ''),

                                              CONSTRAINT chk_tipo_restriccion_competitiva_nombre
                                                  CHECK (TRIM(nombre_oficial) <> '')
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO tipo_restriccion_competitiva (codigo, nombre_oficial)
VALUES
    ('BAN', 'Ban'),
    ('RESTRICT', 'Restrict');


CREATE TABLE restriccion_competitiva_carta (
                                               id BIGINT NOT NULL AUTO_INCREMENT,
                                               carta_id BIGINT NOT NULL,
                                               tipo_restriccion_competitiva_id BIGINT NOT NULL,
                                               maximo_copias INT NOT NULL,
                                               fecha_inicio DATE NOT NULL,
                                               fecha_fin DATE NULL,
                                               nota TEXT NULL,

                                               CONSTRAINT pk_restriccion_competitiva_carta
                                                   PRIMARY KEY (id),

                                               CONSTRAINT uk_restriccion_competitiva_carta_inicio
                                                   UNIQUE (carta_id, fecha_inicio),

                                               CONSTRAINT chk_restriccion_competitiva_maximo
                                                   CHECK (maximo_copias >= 0),

                                               CONSTRAINT chk_restriccion_competitiva_fechas
                                                   CHECK (fecha_fin IS NULL OR fecha_fin > fecha_inicio),

                                               CONSTRAINT fk_restriccion_competitiva_carta
                                                   FOREIGN KEY (carta_id)
                                                       REFERENCES carta(id)
                                                       ON DELETE CASCADE,

                                               CONSTRAINT fk_restriccion_competitiva_tipo
                                                   FOREIGN KEY (tipo_restriccion_competitiva_id)
                                                       REFERENCES tipo_restriccion_competitiva(id)
                                                       ON DELETE RESTRICT
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE restriccion_pareja (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    carta_a_id BIGINT NOT NULL,
                                    carta_b_id BIGINT NOT NULL,
                                    fecha_inicio DATE NOT NULL,
                                    fecha_fin DATE NULL,
                                    nota TEXT NULL,

                                    CONSTRAINT pk_restriccion_pareja
                                        PRIMARY KEY (id),

                                    CONSTRAINT uk_restriccion_pareja_cartas_inicio
                                        UNIQUE (carta_a_id, carta_b_id, fecha_inicio),

                                    CONSTRAINT chk_restriccion_pareja_cartas_distintas
                                        CHECK (carta_a_id < carta_b_id),

                                    CONSTRAINT chk_restriccion_pareja_fechas
                                        CHECK (fecha_fin IS NULL OR fecha_fin > fecha_inicio),

                                    CONSTRAINT fk_restriccion_pareja_carta_a
                                        FOREIGN KEY (carta_a_id)
                                            REFERENCES carta(id)
                                            ON DELETE CASCADE,

                                    CONSTRAINT fk_restriccion_pareja_carta_b
                                        FOREIGN KEY (carta_b_id)
                                            REFERENCES carta(id)
                                            ON DELETE CASCADE
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;