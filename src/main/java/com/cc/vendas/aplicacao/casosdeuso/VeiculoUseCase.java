package com.cc.vendas.aplicacao.casosdeuso;

import com.cc.vendas.aplicacao.dto.VeiculoDTO;
import com.cc.vendas.dominio.veiculo.Veiculo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoUseCase {
    Veiculo atualizarDadosVeiculo(UUID id, VeiculoDTO veiculo);
    Optional<Veiculo> buscarVeiculoPorId(UUID id);
    List<Veiculo> buscarVeiculosDisponiveis();
    List<Veiculo> buscarVeiculosVendidos();
}
