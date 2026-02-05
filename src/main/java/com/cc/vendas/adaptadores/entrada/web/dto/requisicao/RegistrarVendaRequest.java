package com.cc.vendas.adaptadores.entrada.web.dto.requisicao;

import java.util.UUID;

public record RegistrarVendaRequest (
        UUID id,
        UUID idVeiculo,
        Double preco,
        String docComprador
){
}
