package com.cc.vendas.adaptadores.entrada.web.controller;

import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.WebhookPagementoRequest;
import com.cc.vendas.aplicacao.casosdeuso.ConfirmarPagamentoUseCase;
import com.cc.vendas.aplicacao.dto.entrada.ConfirmacaoPagamentoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("webhook/pagamentos")
public class WebhookPagamentoController {

    private final ConfirmarPagamentoUseCase useCase;

    public WebhookPagamentoController(ConfirmarPagamentoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Void> receber (@RequestBody WebhookPagementoRequest request) {

        ConfirmacaoPagamentoInput input = new ConfirmacaoPagamentoInput(
                request.vendaId(),
                request.valor(),
                request.codigoPagamento()
        );

        useCase.confirmar(input);

        return ResponseEntity.ok().build();
    }
}
