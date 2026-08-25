package com.jotilla.paginatcg.repository;

import com.jotilla.paginatcg.entity.Carta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaRepository extends JpaRepository<Carta, Long> {

    Optional<Carta> findByCodigo(String codigo);
}