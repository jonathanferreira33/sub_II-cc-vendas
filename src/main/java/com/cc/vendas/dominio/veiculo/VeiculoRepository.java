package com.cc.vendas.dominio.veiculo;

import com.cc.vendas.adaptadores.saida.entidades.JpaVeiculoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository {
    Veiculo salvar(Veiculo veiculo);
    Optional<Veiculo> buscarPorId(UUID id);
    List<Veiculo> buscarTodosVeiculosPorStatusOrdenadoPorPreco(String status);
    JpaVeiculoEntity buscarEntidadePorId(UUID id);
}
