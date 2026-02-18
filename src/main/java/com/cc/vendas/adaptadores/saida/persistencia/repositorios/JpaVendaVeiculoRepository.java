package com.cc.vendas.adaptadores.saida.persistencia.repositorios;

import com.cc.vendas.adaptadores.saida.entidades.JpaVendaVeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaVendaVeiculoRepository extends JpaRepository<JpaVendaVeiculoEntity, UUID> {
}
