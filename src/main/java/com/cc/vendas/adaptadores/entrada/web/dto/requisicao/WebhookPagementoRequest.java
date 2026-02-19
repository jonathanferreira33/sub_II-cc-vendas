package com.cc.vendas.adaptadores.entrada.web.dto.requisicao;

import com.cc.vendas.shared.StatusPagamento;

import java.util.UUID;

public record WebhookPagementoRequest(
        UUID vendaId,
        Double valor,
        String codigoPagamento
) {
}
