package com.cc.vendas.adaptadores.entrada.web.controlador;

import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.RegistrarVendaRequest;
import com.cc.vendas.adaptadores.entrada.web.dto.resposta.RegistrarVendaResponse;
import com.cc.vendas.aplicacao.casosdeuso.VendaUseCase;
import com.cc.vendas.dominio.venda.VendaVeiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/veiculos")
public class VendaController {
    private final VendaUseCase vendaVeiculoUseCase;

    public VendaController(VendaUseCase vendaVeiculoUseCase) {
        this.vendaVeiculoUseCase = vendaVeiculoUseCase;
    }

    @PostMapping("/{idVeiculo}/vender")
    public ResponseEntity<RegistrarVendaResponse> vender(
            @PathVariable UUID idVeiculo,
            @RequestBody RegistrarVendaRequest requisicao) {

        VendaVeiculo venda = vendaVeiculoUseCase.venda(
                new VendaVeiculo(idVeiculo, requisicao.preco(), requisicao.docComprador()));
        return ResponseEntity.ok().build();
    }

}
