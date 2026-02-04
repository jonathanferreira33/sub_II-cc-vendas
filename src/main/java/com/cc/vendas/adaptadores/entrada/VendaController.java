package com.cc.vendas.adaptadores.entrada;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculos")
public class VendaController {
    private final VendaVeiculoUseCase vendaVeiculoUseCase;

    public VendaController(VendaVeiculoUseCase vendaVeiculoUseCase) {
        this.vendaVeiculoUseCase = vendaVeiculoUseCase;
    }

    @PostMapping("/{id}/vender")
    public ResponseEntity<Void> vender(@PathVariable Long id, @RequestBody VendaDTO dto) {
        vendaVeiculoUseCase.executar(id, dto.getDocComprador());
        return ResponseEntity.ok().build();
    }

}
