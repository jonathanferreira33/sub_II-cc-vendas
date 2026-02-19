package com.cc.vendas.dominio.veiculo;

import com.cc.vendas.dominio.excecao.RegraNegocioException;

import java.time.Instant;
import java.util.UUID;

public class Veiculo {
    private UUID id;
    private String marca;
    private String modelo;
    private String cor;
    private Integer ano;
    private Double preco;
    private StatusVeiculo status;
    private String docComprador;
    private Instant dataVenda;

    public Veiculo() {
        this.status = StatusVeiculo.ANALISE;
    }

    private Veiculo(
            UUID id,
            String marca,
            String modelo,
            String cor,
            Integer ano,
            Double preco
    ) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
        this.status = StatusVeiculo.ANALISE;
    }

    public UUID getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getCor() { return cor; }
    public Integer getAno() { return ano; }
    public Double getPreco() { return preco; }
    public StatusVeiculo getStatus() { return status; }
    public String getDocComprador() { return docComprador; }
    public Instant getDataVenda() { return dataVenda; }

    private void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    public static Veiculo criar(
            String marca,
            String modelo,
            String cor,
            Integer ano,
            Double preco
    ) {
        validarCampos(marca, modelo, cor, ano, preco);
        return new Veiculo(
                UUID.randomUUID(),
                marca,
                modelo,
                cor,
                ano,
                preco
        );
    }

    public static Veiculo reconstituir(
            UUID id,
            String marca,
            String modelo,
            String cor,
            Integer ano,
            Double preco,
            StatusVeiculo status,
            String docComprador,
            Instant dataVenda
    ) {
        Veiculo veiculo = new Veiculo(
                id,
                marca,
                modelo,
                cor,
                ano,
                preco
        );

        veiculo.status = status;
        veiculo.docComprador = docComprador;

        if (dataVenda != null)
            veiculo.dataVenda = dataVenda;

        return veiculo;
    }

    public void atualizarDados(
            String marca,
            String modelo,
            String cor,
            Integer ano,
            Double preco
    ) {
        validarEdicao();
        validarCampos(marca, modelo, cor, ano, preco);

        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.preco = preco;
    }

    public void alterarStatusParaDisponivel() {
        setStatus(StatusVeiculo.DISPONIVEL_PARA_VENDA);
    }

    public void registrarVenda(String cpf) {
        if (this.status != StatusVeiculo.DISPONIVEL_PARA_VENDA)
            throw new RegraNegocioException("Veiculo indisponivel para venda");

        this.docComprador = cpf;
        this.status = StatusVeiculo.VENDIDO;
        this.dataVenda = Instant.now();
    }


    public void validarEdicao() {
        if (this.status == StatusVeiculo.VENDIDO)
            throw new RegraNegocioException("Veiculo indisponivel para edição");
    }

    private static void validarCampos(
            String marca,
            String modelo,
            String cor,
            Integer ano,
            Double preco
    ) {
        if (marca == null || marca.isBlank())
            throw new RegraNegocioException("Marca obrigatória");

        if (modelo == null || modelo.isBlank())
            throw new RegraNegocioException("Modelo obrigatório");

        if (cor == null || cor.isBlank())
            throw new RegraNegocioException("Modelo obrigatório");

        if (ano == null || ano <= 0)
            throw new RegraNegocioException("Ano inválido");

        if (preco == null || preco <= 0)
            throw new RegraNegocioException("Preço inválido");
    }
}
