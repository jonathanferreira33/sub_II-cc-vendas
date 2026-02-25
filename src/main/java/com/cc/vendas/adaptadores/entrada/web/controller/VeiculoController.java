package com.cc.vendas.adaptadores.entrada.web.controller;

import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.AtualizarVeiculoRequest;
import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.RegistrarVeiculoRequest;
import com.cc.vendas.adaptadores.entrada.web.dto.resposta.VeiculoResumoResponse;
import com.cc.vendas.adaptadores.entrada.web.mapper.VeiculoMapperWeb;
import com.cc.vendas.aplicacao.casosdeuso.VeiculoUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/veiculos")
@Tag(name = "Vendas", description = "Operações relacionadas à venda de veículos")
public class VeiculoController {

    private final VeiculoUseCase useCase;

    public VeiculoController(VeiculoUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<VeiculoResumoResponse>> buscarVeiculosDisponiveis () {
        return ResponseEntity.ok(
                VeiculoMapperWeb.listaResumoOutputParaResponse(
                        useCase.buscarVeiculosDisponiveis()
                )
        );
    }

    @GetMapping("/vendidos")
    public ResponseEntity<List<VeiculoResumoResponse>> buscarVeiculosVendidos () {
        return ResponseEntity.ok(
                VeiculoMapperWeb.listaResumoOutputParaResponse(
                        useCase.buscarVeiculosVendidos()
                )
        );
    }

    @GetMapping("/{idVeiculo}")
    public ResponseEntity<VeiculoResumoResponse> buscarVeiculo (
            @Parameter(description = "ID do veículo", required = true)
            @PathVariable UUID idVeiculo) {
        return useCase.buscarVeiculoPorId(idVeiculo)
                .map(VeiculoMapperWeb::resumoOutputParaResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Registrar venda de um veículo",
            description = "Registra a venda de um veículo informando o CPF do comprador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda registrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<VeiculoResumoResponse> cadastrar(@RequestBody RegistrarVeiculoRequest veiculoRequest) {
        var output = useCase.cadastrarVeiculo(
                VeiculoMapperWeb.registrarVeiculoRequestParaInput(veiculoRequest)
        );

        var response = VeiculoMapperWeb.resumoOutputParaResponse(output);

        return ResponseEntity
                .created(URI.create("/api/veiculos/" + response.id()))
                .body(response);
    }

    @PutMapping("/{idVeiculo}")
    public ResponseEntity<VeiculoResumoResponse> atualizar(
            @Parameter(description = "ID do veículo", required = true)
            @PathVariable UUID idVeiculo,
            @RequestBody AtualizarVeiculoRequest request) {

        var output = useCase.atualizarDadosVeiculo(
                idVeiculo,
                VeiculoMapperWeb.atualizarRequestParaAtualizarInput(request)
        );

        var response = VeiculoMapperWeb.resumoOutputParaResponse(output);

        return ResponseEntity.ok(response);
    }

}
