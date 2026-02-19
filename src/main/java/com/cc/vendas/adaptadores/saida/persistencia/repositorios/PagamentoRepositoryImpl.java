package com.cc.vendas.adaptadores.saida.persistencia.repositorios;

import com.cc.vendas.adaptadores.saida.entidades.JpaPagamentoEntity;
import com.cc.vendas.adaptadores.saida.mapper.PagamentoMapper;
import com.cc.vendas.dominio.pagamento.Pagamento;
import com.cc.vendas.dominio.pagamento.PagamentoRepository;

import java.util.Optional;
import java.util.UUID;

public class PagamentoRepositoryImpl implements PagamentoRepository {

    private final JpaPagamentoRepository repository;

    public PagamentoRepositoryImpl(JpaPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagamento cadastrarPagamento(Pagamento pagamento) {
        JpaPagamentoEntity entity = PagamentoMapper.dominioParaJpa(pagamento);
        this.repository.save(entity);
        return PagamentoMapper.jpaParaDominio(entity);
    }

    @Override
    public Optional<Pagamento> buscarPagamentoPorId(UUID id) {
        return repository.findById(id)
                .map(PagamentoMapper::jpaParaDominio);
    }
}
