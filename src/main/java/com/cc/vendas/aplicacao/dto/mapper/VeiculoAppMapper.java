package com.cc.vendas.aplicacao.dto.mapper;

import com.cc.vendas.aplicacao.dto.saida.VeiculoResumoOutput;
import com.cc.vendas.dominio.veiculo.Veiculo;

public class VeiculoAppMapper {

    public static VeiculoResumoOutput veiculoParaResumoOutput(Veiculo veiculo) {
        return new VeiculoResumoOutput(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getAno(),
                veiculo.getPreco(),
                veiculo.getStatus(),
                veiculo.getDataVenda()
        );
    }
}
