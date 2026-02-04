package com.cc.vendas.adaptadores.entrada;

import com.cc.vendas.aplicacao.casosdeuso.VeiculoUseCase;
import com.cc.vendas.dominio.veiculo.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/veiculos")
public class VeiculoController {

    private final VeiculoUseCase useCase;

    public VeiculoController(VeiculoUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizarVeiculoRequest request) {

        Veiculo veiculo = useCase.atualizarDadosVeiculo(id, request);

        return ResponseEntity.ok(VeiculoResponse.from(veiculo));
    }
}
