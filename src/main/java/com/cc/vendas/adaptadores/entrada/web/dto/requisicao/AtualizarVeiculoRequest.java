package com.cc.vendas.adaptadores.entrada.web.dto.requisicao;

import com.cc.vendas.dominio.veiculo.StatusVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizarVeiculoRequest(
        @NotBlank String marca,
        @NotBlank String modelo,
        @NotBlank String cor,
        @NotNull Integer ano,
        @Positive Double preco,
        StatusVeiculo status
) {
}
