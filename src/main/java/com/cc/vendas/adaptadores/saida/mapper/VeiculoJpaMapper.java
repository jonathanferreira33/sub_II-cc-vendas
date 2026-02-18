package com.cc.vendas.adaptadores.saida.mapper;

import com.cc.vendas.adaptadores.saida.entidades.JpaVeiculoEntity;
import com.cc.vendas.dominio.veiculo.StatusVeiculo;
import com.cc.vendas.dominio.veiculo.Veiculo;

import java.util.UUID;

public class VeiculoJpaMapper {

    public static JpaVeiculoEntity dominioParaJpa(Veiculo veiculo) {
        return new JpaVeiculoEntity(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getAno(),
                veiculo.getPreco(),
                veiculo.getStatus().name(),
                veiculo.getDocComprador()
            );
        }

    public static Veiculo jpaParaDominio(JpaVeiculoEntity entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Entidade JPA de Veículo não pode ser nula");
        }

        return Veiculo.reconstituir(
                entidade.getId(),
                entidade.getMarca(),
                entidade.getModelo(),
                entidade.getCor(),
                entidade.getAno(),
                entidade.getPreco(),
                StatusVeiculo.valueOf(entidade.getStatusVeiculo()),
                entidade.getDocComprador()
        );
    }
}

