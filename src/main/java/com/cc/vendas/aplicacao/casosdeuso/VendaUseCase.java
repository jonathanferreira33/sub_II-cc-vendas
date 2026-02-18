package com.cc.vendas.aplicacao.casosdeuso;


import java.util.UUID;

public interface VendaUseCase {
    void registrarVenda(UUID id, String docComprador);
}
