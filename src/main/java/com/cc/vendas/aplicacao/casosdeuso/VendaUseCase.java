package com.cc.vendas.aplicacao.casosdeuso;

import com.cc.vendas.aplicacao.dto.saida.VendaResumoOutput;

import java.util.UUID;

public interface VendaUseCase {
    void registrarVenda(UUID id, String docComprador);
}
