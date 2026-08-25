CREATE TABLE carta (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       codigo VARCHAR(30) NOT NULL,
                       nombre_general VARCHAR(255) NOT NULL,
                       categoria_carta VARCHAR(50) NOT NULL,
                       rareza_base VARCHAR(20) NULL,
                       icono_bloque VARCHAR(10) NULL,
                       limite_copias_regla INT NOT NULL DEFAULT 4,

                       PRIMARY KEY (id),
                       CONSTRAINT uk_carta_codigo UNIQUE (codigo),
                       CONSTRAINT chk_carta_limite_copias
                           CHECK (limite_copias_regla >= 0)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;