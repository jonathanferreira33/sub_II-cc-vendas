package com.cc.vendas.adaptadores.entrada.web.controller;

import com.cc.vendas.aplicacao.casosdeuso.VendaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RestController
@RequestMapping("api/vendas")
public class VendaController {
    private final VendaUseCase vendaVeiculoUseCase;

    public VendaController(VendaUseCase vendaVeiculoUseCase) {
        this.vendaVeiculoUseCase = vendaVeiculoUseCase;
    }

    @PostMapping("/{idVeiculo}/registrar-venda")
    public ResponseEntity<Void> registrarVenda(
            @PathVariable UUID idVeiculo,
            @RequestParam String cpfComprador) {

        vendaVeiculoUseCase.registrarVenda(idVeiculo, cpfComprador);
        return ResponseEntity.noContent().build();
    }

}
