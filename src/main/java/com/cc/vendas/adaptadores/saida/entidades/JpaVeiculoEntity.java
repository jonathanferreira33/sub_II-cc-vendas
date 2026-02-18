package com.cc.vendas.adaptadores.saida.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_veiculos")
@Data
public class JpaVeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String marca;
    private String modelo;
    private String cor;
    private Integer ano;
    private Double preco;
    private String statusVeiculo;

    @Column
    private String docComprador;

    public JpaVeiculoEntity(String marca, String modelo, String cor, Integer ano, Double preco, String statusVeiculo, String docComprador) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
        this.statusVeiculo = statusVeiculo;
        this.docComprador = docComprador;
    }
}
