CREATE TABLE fuente_importacion (
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      codigo VARCHAR(50) NOT NULL,
                                      nombre VARCHAR(100) NOT NULL,
                                      url_base VARCHAR(2048) NULL,

                                      CONSTRAINT pk_fuente_importacion
                                          PRIMARY KEY (id),

                                      CONSTRAINT uk_fuente_importacion_codigo
                                          UNIQUE (codigo),

                                      CONSTRAINT uk_fuente_importacion_nombre
                                          UNIQUE (nombre),

                                      CONSTRAINT chk_fuente_importacion_codigo
                                          CHECK (CHAR_LENGTH(TRIM(codigo)) > 0),

                                      CONSTRAINT chk_fuente_importacion_nombre
                                          CHECK (CHAR_LENGTH(TRIM(nombre)) > 0),

                                      CONSTRAINT chk_fuente_importacion_url_base
                                          CHECK (
                                              url_base IS NULL
                                                  OR CHAR_LENGTH(TRIM(url_base)) > 0
                                              )
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


INSERT INTO fuente_importacion (codigo, nombre, url_base)
VALUES ('HEROICC', 'Heroicc', 'https://heroi.cc');


CREATE TABLE lote_importacion (
                                    id BIGINT NOT NULL AUTO_INCREMENT,
                                    fuente_importacion_id BIGINT NOT NULL,
                                    idioma_id BIGINT NULL,
                                    tipo_datos VARCHAR(100) NOT NULL,
                                    identificador_fuente VARCHAR(255) NOT NULL,
                                    url_fuente VARCHAR(2048) NULL,
                                    hash_sha256 CHAR(64) NULL,
                                    fecha_datos DATE NULL,
                                    iniciado_en DATETIME(6) NOT NULL,
                                    finalizado_en DATETIME(6) NULL,
                                    estado VARCHAR(50) NOT NULL,
                                    total_registros_fuente INT NULL,
                                    total_registros_procesados INT NOT NULL DEFAULT 0,
                                    total_inserciones INT NOT NULL DEFAULT 0,
                                    total_actualizaciones INT NOT NULL DEFAULT 0,
                                    total_omisiones INT NOT NULL DEFAULT 0,
                                    total_advertencias INT NOT NULL DEFAULT 0,
                                    total_errores INT NOT NULL DEFAULT 0,
                                    notas TEXT NULL,

                                    CONSTRAINT pk_lote_importacion
                                        PRIMARY KEY (id),

                                    INDEX idx_lote_importacion_fuente_identificador
                                        (fuente_importacion_id, identificador_fuente),

                                    CONSTRAINT fk_lote_importacion_fuente
                                        FOREIGN KEY (fuente_importacion_id)
                                            REFERENCES fuente_importacion (id)
                                            ON DELETE RESTRICT,

                                    CONSTRAINT fk_lote_importacion_idioma
                                        FOREIGN KEY (idioma_id)
                                            REFERENCES idioma (id)
                                            ON DELETE RESTRICT,

                                    CONSTRAINT chk_lote_importacion_tipo_datos
                                        CHECK (CHAR_LENGTH(TRIM(tipo_datos)) > 0),

                                    CONSTRAINT chk_lote_importacion_identificador
                                        CHECK (CHAR_LENGTH(TRIM(identificador_fuente)) > 0),

                                    CONSTRAINT chk_lote_importacion_url_fuente
                                        CHECK (
                                            url_fuente IS NULL
                                                OR CHAR_LENGTH(TRIM(url_fuente)) > 0
                                            ),

                                    CONSTRAINT chk_lote_importacion_hash_sha256
                                        CHECK (
                                            hash_sha256 IS NULL
                                                OR (
                                                    CHAR_LENGTH(hash_sha256) = 64
                                                        AND hash_sha256 REGEXP '^[0-9A-Fa-f]{64}$'
                                                    )
                                            ),

                                    CONSTRAINT chk_lote_importacion_fechas
                                        CHECK (
                                            finalizado_en IS NULL
                                                OR finalizado_en >= iniciado_en
                                            ),

                                    CONSTRAINT chk_lote_importacion_estado
                                        CHECK (CHAR_LENGTH(TRIM(estado)) > 0),

                                    CONSTRAINT chk_lote_importacion_total_fuente
                                        CHECK (
                                            total_registros_fuente IS NULL
                                                OR total_registros_fuente >= 0
                                            ),

                                    CONSTRAINT chk_lote_importacion_total_procesados
                                        CHECK (total_registros_procesados >= 0),

                                    CONSTRAINT chk_lote_importacion_total_inserciones
                                        CHECK (total_inserciones >= 0),

                                    CONSTRAINT chk_lote_importacion_total_actualizaciones
                                        CHECK (total_actualizaciones >= 0),

                                    CONSTRAINT chk_lote_importacion_total_omisiones
                                        CHECK (total_omisiones >= 0),

                                    CONSTRAINT chk_lote_importacion_total_advertencias
                                        CHECK (total_advertencias >= 0),

                                    CONSTRAINT chk_lote_importacion_total_errores
                                        CHECK (total_errores >= 0),

                                    INDEX idx_lote_importacion_fuente_tipo
                                        (fuente_importacion_id, tipo_datos),

                                    INDEX idx_lote_importacion_estado
                                        (estado),

                                    INDEX idx_lote_importacion_iniciado
                                        (iniciado_en)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE incidencia_importacion (
                                          id BIGINT NOT NULL AUTO_INCREMENT,
                                          lote_importacion_id BIGINT NOT NULL,
                                          severidad VARCHAR(20) NOT NULL,
                                          codigo VARCHAR(100) NOT NULL,
                                          referencia_fuente VARCHAR(255) NULL,
                                          entidad_destino VARCHAR(100) NULL,
                                          mensaje VARCHAR(500) NOT NULL,
                                          detalle TEXT NULL,
                                          creado_en DATETIME(6) NOT NULL,

                                          CONSTRAINT pk_incidencia_importacion
                                              PRIMARY KEY (id),

                                          CONSTRAINT fk_incidencia_importacion_lote
                                              FOREIGN KEY (lote_importacion_id)
                                                  REFERENCES lote_importacion (id)
                                                  ON DELETE CASCADE,

                                          CONSTRAINT chk_incidencia_importacion_severidad
                                              CHECK (CHAR_LENGTH(TRIM(severidad)) > 0),

                                          CONSTRAINT chk_incidencia_importacion_codigo
                                              CHECK (CHAR_LENGTH(TRIM(codigo)) > 0),

                                          CONSTRAINT chk_incidencia_importacion_referencia
                                              CHECK (
                                                  referencia_fuente IS NULL
                                                      OR CHAR_LENGTH(TRIM(referencia_fuente)) > 0
                                                  ),

                                          CONSTRAINT chk_incidencia_importacion_entidad
                                              CHECK (
                                                  entidad_destino IS NULL
                                                      OR CHAR_LENGTH(TRIM(entidad_destino)) > 0
                                                  ),

                                          CONSTRAINT chk_incidencia_importacion_mensaje
                                              CHECK (CHAR_LENGTH(TRIM(mensaje)) > 0),

                                          INDEX idx_incidencia_importacion_lote_severidad
                                              (lote_importacion_id, severidad),

                                          INDEX idx_incidencia_importacion_referencia
                                              (referencia_fuente),

                                          INDEX idx_incidencia_importacion_codigo
                                              (codigo)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
