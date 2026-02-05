package com.cc.vendas.dominio.venda;

import java.util.UUID;

public class VendaVeiculo {
    private UUID id;
    private UUID idVeiculo;
    private Double preco;
    private String docComprador;

    public VendaVeiculo() {
    }

    public VendaVeiculo(UUID id, UUID idVeiculo, Double preco, String docComprador) {
        this.id = id;
        this.idVeiculo = idVeiculo;
        this.preco = preco;
        this.docComprador = docComprador;
    }

    public VendaVeiculo(UUID idVeiculo, Double preco, String docComprador) {
        this.idVeiculo = idVeiculo;
        this.preco = preco;
        this.docComprador = docComprador;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(UUID idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDocComprador() {
        return docComprador;
    }

    public void setDocComprador(String docComprador) {
        this.docComprador = docComprador;
    }
}
