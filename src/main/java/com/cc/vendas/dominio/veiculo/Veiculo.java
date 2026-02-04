package com.cc.vendas.dominio.veiculo;

import java.util.UUID;

public class Veiculo {
    private UUID id;
    private String marca;
    private String modelo;
    private Integer ano;
    private Double preco;
    private StatusVeiculo status;
    private String docComprador;

    public Veiculo() {
        this.status = StatusVeiculo.ANALISE;
    }

    public Veiculo(UUID id, String marca, String modelo, Integer ano, Double preco, StatusVeiculo status, String docComprador) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.status = status;
        this.docComprador = docComprador;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    public String getDocComprador() {
        return docComprador;
    }

    public void setDocComprador(String docComprador) {
        this.docComprador = docComprador;
    }

    public void validarEdicao() throws Exception {
        if (this.status == StatusVeiculo.VENDIDO)
            throw new Exception("Veiculo indisponivel para edição");
    }

    public void registrarVenda(String cpf) throws Exception {
        if (this.status != StatusVeiculo.DISPONIVEL_PARA_VENDA)
            throw new Exception("Veiculo indisponivel para venda");

        this.docComprador = cpf;
        this.status = StatusVeiculo.VENDIDO;
    }

}
