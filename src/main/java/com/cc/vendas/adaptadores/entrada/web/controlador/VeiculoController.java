package com.cc.vendas.adaptadores.entrada.web.controlador;

import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.AtualizarVeiculoRequest;
import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.RegistrarVeiculoRequest;
import com.cc.vendas.adaptadores.entrada.web.dto.resposta.VeiculoResumoResponse;
import com.cc.vendas.adaptadores.entrada.web.mapper.VeiculoMapperWeb;
import com.cc.vendas.aplicacao.casosdeuso.VeiculoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/veiculos")
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
    public ResponseEntity<VeiculoResumoResponse> buscarVeiculo (@PathVariable UUID idVeiculo) {
        return useCase.buscarVeiculoPorId(idVeiculo)
                .map(VeiculoMapperWeb::resumoOutputParaResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

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
