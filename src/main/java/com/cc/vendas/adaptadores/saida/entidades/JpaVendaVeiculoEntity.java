package com.cc.vendas.adaptadores.saida.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_vendas_veiculo")
@Data
public class JpaVendaVeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID idVeiculo;
    private Double preco;
    private String docComprador;
    private LocalDateTime dataCompra;

    public JpaVendaVeiculoEntity(UUID idVeiculo, Double preco, String docComprador, LocalDateTime dataCompra) {
        this.idVeiculo = idVeiculo;
        this.preco = preco;
        this.docComprador = docComprador;
        this.dataCompra = dataCompra;
    }
}
