package com.cc.vendas.adaptadores.saida.persistencia.mapper;

import com.cc.vendas.adaptadores.saida.entidades.JpaVeiculoEntity;
import com.cc.vendas.dominio.veiculo.StatusVeiculo;
import com.cc.vendas.dominio.veiculo.Veiculo;

public class VeiculoMapper {

    public static JpaVeiculoEntity jpaParaDominio(Veiculo dominio) {
        var entidade = new JpaVeiculoEntity();
        entidade.setId(dominio.getId());
        entidade.setMarca(dominio.getMarca());
        entidade.setModelo(dominio.getModelo());
        entidade.setAno(dominio.getAno());
        entidade.setPreco(dominio.getPreco());
        entidade.setStatusVeiculo(dominio.getStatus().name());

        if (dominio.getDocComprador() != null) {
            entidade.setDocComprador(dominio.getDocComprador());
        }

        return entidade;
    }

    public static Veiculo jpaParaDominio(JpaVeiculoEntity entidade) {
        var dominio = new Veiculo();

        dominio.setId(entidade.getId());
        dominio.setMarca(entidade.getMarca());
        dominio.setModelo(entidade.getModelo());
        dominio.setAno(entidade.getAno());
        dominio.setPreco(entidade.getPreco());

        if (entidade.getStatusVeiculo() != null)
            dominio.setStatus(StatusVeiculo.valueOf(entidade.getStatusVeiculo()));

        if (entidade.getDocComprador() != null)
            dominio.setDocComprador(entidade.getDocComprador());

        return dominio;
    }
}
