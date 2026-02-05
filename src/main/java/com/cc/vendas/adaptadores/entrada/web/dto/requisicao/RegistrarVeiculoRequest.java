package com.cc.vendas.adaptadores.entrada.web.dto.requisicao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistrarVeiculoRequest (
        @NotBlank String marca,
        @NotBlank String modelo,
        @NotBlank String cor,
        @NotNull Integer ano,
        @Positive Double preco
) {
}
