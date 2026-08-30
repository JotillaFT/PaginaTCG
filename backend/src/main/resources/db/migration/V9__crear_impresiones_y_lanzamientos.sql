CREATE TABLE idioma (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        codigo VARCHAR(20) NOT NULL,
                        nombre VARCHAR(100) NOT NULL,

                        PRIMARY KEY (id),

                        CONSTRAINT uk_idioma_codigo
                            UNIQUE (codigo),

                        CONSTRAINT chk_idioma_codigo_no_vacio
                            CHECK (CHAR_LENGTH(TRIM(codigo)) > 0),

                        CONSTRAINT chk_idioma_nombre_no_vacio
                            CHECK (CHAR_LENGTH(TRIM(nombre)) > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO idioma (codigo, nombre)
VALUES
    ('EN', 'English'),
    ('JA', 'Japanese'),
    ('KO', 'Korean'),
    ('ZH_HANS', 'Simplified Chinese');


CREATE TABLE lanzamiento (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             idioma_id BIGINT NOT NULL,
                             codigo VARCHAR(150) NOT NULL,
                             nombre VARCHAR(255) NOT NULL,
                             genero VARCHAR(100) NULL,
                             fecha DATE NULL,
                             url_producto VARCHAR(2048) NULL,
                             url_lista_cartas VARCHAR(2048) NULL,
                             url_imagen VARCHAR(2048) NULL,
                             url_miniatura VARCHAR(2048) NULL,

                             PRIMARY KEY (id),

                             CONSTRAINT uk_lanzamiento_idioma_codigo
                                 UNIQUE (idioma_id, codigo),

                             CONSTRAINT fk_lanzamiento_idioma
                                 FOREIGN KEY (idioma_id)
                                     REFERENCES idioma (id)
                                     ON DELETE RESTRICT,

                             CONSTRAINT chk_lanzamiento_codigo_no_vacio
                                 CHECK (CHAR_LENGTH(TRIM(codigo)) > 0),

                             CONSTRAINT chk_lanzamiento_nombre_no_vacio
                                 CHECK (CHAR_LENGTH(TRIM(nombre)) > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE impresion_carta (
                                 id BIGINT NOT NULL AUTO_INCREMENT,
                                 carta_id BIGINT NOT NULL,
                                 idioma_id BIGINT NOT NULL,
                                 numero_variante INT NOT NULL DEFAULT 0,
                                 url_imagen VARCHAR(2048) NULL,
                                 notas TEXT NULL,
                                 estrellas INT NULL,
                                 sello VARCHAR(50) NULL,

                                 PRIMARY KEY (id),

                                 CONSTRAINT uk_impresion_carta_carta_idioma_variante
                                     UNIQUE (carta_id, idioma_id, numero_variante),

                                 CONSTRAINT fk_impresion_carta_carta
                                     FOREIGN KEY (carta_id)
                                         REFERENCES carta (id)
                                         ON DELETE CASCADE,

                                 CONSTRAINT fk_impresion_carta_idioma
                                     FOREIGN KEY (idioma_id)
                                         REFERENCES idioma (id)
                                         ON DELETE RESTRICT,

                                 CONSTRAINT chk_impresion_carta_numero_variante
                                     CHECK (numero_variante >= 0),

                                 CONSTRAINT chk_impresion_carta_estrellas
                                     CHECK (estrellas IS NULL OR estrellas >= 0),

                                 CONSTRAINT chk_impresion_carta_url_imagen
                                     CHECK (
                                         url_imagen IS NULL
                                             OR CHAR_LENGTH(TRIM(url_imagen)) > 0
                                         ),

                                 CONSTRAINT chk_impresion_carta_sello
                                     CHECK (
                                         sello IS NULL
                                             OR CHAR_LENGTH(TRIM(sello)) > 0
                                         )
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE impresion_carta_lanzamiento (
                                             id BIGINT NOT NULL AUTO_INCREMENT,
                                             impresion_carta_id BIGINT NOT NULL,
                                             lanzamiento_id BIGINT NOT NULL,

                                             PRIMARY KEY (id),

                                             CONSTRAINT uk_impresion_carta_lanzamiento
                                                 UNIQUE (impresion_carta_id, lanzamiento_id),

                                             CONSTRAINT fk_impresion_carta_lanzamiento_impresion
                                                 FOREIGN KEY (impresion_carta_id)
                                                     REFERENCES impresion_carta (id)
                                                     ON DELETE CASCADE,

                                             CONSTRAINT fk_impresion_carta_lanzamiento_lanzamiento
                                                 FOREIGN KEY (lanzamiento_id)
                                                     REFERENCES lanzamiento (id)
                                                     ON DELETE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE ilustrador (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            nombre_credito VARCHAR(255) NOT NULL,

                            PRIMARY KEY (id),

                            CONSTRAINT uk_ilustrador_nombre_credito
                                UNIQUE (nombre_credito),

                            CONSTRAINT chk_ilustrador_nombre_credito_no_vacio
                                CHECK (CHAR_LENGTH(TRIM(nombre_credito)) > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE impresion_carta_ilustrador (
                                            id BIGINT NOT NULL AUTO_INCREMENT,
                                            impresion_carta_id BIGINT NOT NULL,
                                            ilustrador_id BIGINT NOT NULL,
                                            orden INT NOT NULL,

                                            PRIMARY KEY (id),

                                            CONSTRAINT uk_impresion_carta_ilustrador_impresion_ilustrador
                                                UNIQUE (impresion_carta_id, ilustrador_id),

                                            CONSTRAINT uk_impresion_carta_ilustrador_impresion_orden
                                                UNIQUE (impresion_carta_id, orden),

                                            CONSTRAINT fk_impresion_carta_ilustrador_impresion
                                                FOREIGN KEY (impresion_carta_id)
                                                    REFERENCES impresion_carta (id)
                                                    ON DELETE CASCADE,

                                            CONSTRAINT fk_impresion_carta_ilustrador_ilustrador
                                                FOREIGN KEY (ilustrador_id)
                                                    REFERENCES ilustrador (id)
                                                    ON DELETE RESTRICT,

                                            CONSTRAINT chk_impresion_carta_ilustrador_orden
                                                CHECK (orden > 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;