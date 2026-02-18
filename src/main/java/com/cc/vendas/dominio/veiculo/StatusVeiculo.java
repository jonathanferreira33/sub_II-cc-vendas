package com.cc.vendas.dominio.veiculo;

import com.cc.vendas.dominio.excecao.RegraNegocioException;

public enum StatusVeiculo {
    ANALISE,
    DISPONIVEL_PARA_VENDA,
    VENDIDO;

    public static StatusVeiculo fromString(String status) {
        if (status == null || status.isBlank()) {
            throw new RegraNegocioException("Status do veículo não informado");
        }

        return switch (status.trim().toLowerCase()) {
            case "analise" -> ANALISE;
            case "disponivel_para_venda", "disponivel", "disponível" -> DISPONIVEL_PARA_VENDA;
            case "vendido" -> VENDIDO;
            default -> throw new RegraNegocioException(
                    "Status do veículo inválido: " + status
            );
        };
    }
}
