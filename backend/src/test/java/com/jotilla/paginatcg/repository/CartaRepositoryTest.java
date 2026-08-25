package com.jotilla.paginatcg.repository;

import com.jotilla.paginatcg.entity.Carta;
import com.jotilla.paginatcg.entity.CategoriaCarta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartaRepositoryTest {

    @Autowired
    private CartaRepository cartaRepository;

    @Test
    void guardaYBuscaUnaCartaPorCodigo() {
        Carta carta = new Carta(
                "TEST-001",
                "Agumon de prueba",
                CategoriaCarta.DIGIMON
        );

        carta.setRarezaBase("C");

        Carta guardada = cartaRepository.saveAndFlush(carta);
        Optional<Carta> encontrada =
                cartaRepository.findByCodigo("TEST-001");

        assertNotNull(guardada.getId());
        assertTrue(encontrada.isPresent());
        assertEquals(
                "Agumon de prueba",
                encontrada.get().getNombreGeneral()
        );
        assertEquals(4, encontrada.get().getLimiteCopiasRegla());
    }
}