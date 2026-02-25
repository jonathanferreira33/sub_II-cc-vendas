package com.cc.vendas.adaptadores.saida.persistencia.repositorios;

import com.cc.vendas.adaptadores.saida.entidades.JpaVendaVeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaVendaVeiculoRepository extends JpaRepository<JpaVendaVeiculoEntity, UUID> {
}
