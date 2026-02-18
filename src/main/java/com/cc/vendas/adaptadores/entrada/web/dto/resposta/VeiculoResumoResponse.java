package com.cc.vendas.adaptadores.entrada.web.dto.resposta;

import com.cc.vendas.dominio.veiculo.StatusVeiculo;

import java.util.UUID;

public record VeiculoResumoResponse(
        UUID id,
        String marca,
        String modelo,
        String cor,
        Integer ano,
        Double preco,
        StatusVeiculo status
) {}
