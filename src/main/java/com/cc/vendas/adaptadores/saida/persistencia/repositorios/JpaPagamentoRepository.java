package com.cc.vendas.adaptadores.saida.persistencia.repositorios;

import com.cc.vendas.adaptadores.saida.entidades.JpaPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPagamentoRepository extends JpaRepository <JpaPagamentoEntity, UUID> {

}
