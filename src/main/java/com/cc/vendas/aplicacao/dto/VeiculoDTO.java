package com.cc.vendas.aplicacao.dto;

import com.cc.vendas.dominio.veiculo.StatusVeiculo;

public class VeiculoDTO {
    private String marca;
    private String modelo;
    private Integer ano;
    private Double preco;
    private StatusVeiculo status;
    private String docComprador;

    public VeiculoDTO(String marca, String modelo, Integer ano, Double preco, StatusVeiculo status, String docComprador) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.status = status;
        this.docComprador = docComprador;
    }

    public VeiculoDTO() {
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
}
