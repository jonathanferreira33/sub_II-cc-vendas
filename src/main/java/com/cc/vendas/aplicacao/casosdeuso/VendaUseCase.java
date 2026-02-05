package com.cc.vendas.aplicacao.casosdeuso;

import com.cc.vendas.dominio.venda.VendaVeiculo;

import java.util.UUID;

public interface VendaUseCase {
    public VendaVeiculo venda(VendaVeiculo vendaVeiculo);
}
