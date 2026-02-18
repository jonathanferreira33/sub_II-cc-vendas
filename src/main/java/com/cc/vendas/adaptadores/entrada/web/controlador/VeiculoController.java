package com.cc.vendas.adaptadores.entrada.web.controlador;

import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.AtualizarVeiculoRequest;
import com.cc.vendas.adaptadores.entrada.web.dto.requisicao.RegistrarVeiculoRequest;
import com.cc.vendas.adaptadores.entrada.web.dto.resposta.VeiculoResumoResponse;
import com.cc.vendas.adaptadores.entrada.web.mapper.VeiculoWebMapper;
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

    @GetMapping("/listar-veiculos-disponiveis")
    public ResponseEntity<List<VeiculoResumoResponse>> listarDisponiveis () {
        return ResponseEntity.ok(
                VeiculoWebMapper.listaResumoOutputParaResponse(
                        useCase.buscarVeiculosDisponiveis()
                )
        );
    }

    @GetMapping("/listar-veiculos-vendidos")
    public ResponseEntity<List<VeiculoResumoResponse>> listarVendidos () {
        return ResponseEntity.ok(
                VeiculoWebMapper.listaResumoOutputParaResponse(
                        useCase.buscarVeiculosVendidos()
                )
        );
    }

    @GetMapping("/{idVeiculo}/encontrar-veiculo")
    public ResponseEntity<VeiculoResumoResponse> listarVendidos (@PathVariable UUID idVeiculo) {
        return useCase.buscarVeiculoPorId(idVeiculo)
                .map(VeiculoWebMapper::resumoOutputParaResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VeiculoResumoResponse> cadastrar(RegistrarVeiculoRequest veiculoRequest) {
        var output = useCase.cadastrarVeiculo(
                VeiculoWebMapper.registrarVeiculoRequestParaInput(veiculoRequest)
        );

        var response = VeiculoWebMapper.resumoOutputParaResponse(output);

        return ResponseEntity
                .created(URI.create("/api/veiculos/" + response.id()))
                .body(response);
    }

    @PutMapping("/{idVeiculo}/editar")
    public ResponseEntity<VeiculoResumoResponse> atualizar(
            @PathVariable UUID idVeiculo,
            @RequestBody AtualizarVeiculoRequest request) {

        var output = useCase.atualizarDadosVeiculo(
                idVeiculo,
                VeiculoWebMapper.atualizarRequestParaAtualizarInput(request)
        );

        var response = VeiculoWebMapper.resumoOutputParaResponse(output);

        return ResponseEntity.ok(response);
    }

}
