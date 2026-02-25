package com.cc.vendas.adaptadores.saida.persistencia.repositorios;

import com.cc.vendas.adaptadores.saida.entidades.JpaPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPagamentoRepository extends JpaRepository <JpaPagamentoEntity, UUID> {
    JpaPagamentoEntity findByCodigoPagamento(String codPagamento);
}
