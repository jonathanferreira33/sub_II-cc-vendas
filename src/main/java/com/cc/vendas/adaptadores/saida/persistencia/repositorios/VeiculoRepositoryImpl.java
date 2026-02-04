package com.cc.vendas.adaptadores.saida.persistencia.repositorios;

import com.cc.vendas.adaptadores.saida.entidades.JpaVeiculoEntity;
import com.cc.vendas.adaptadores.saida.persistencia.mapper.VeiculoMapper;
import com.cc.vendas.dominio.veiculo.Veiculo;
import com.cc.vendas.dominio.veiculo.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class VeiculoRepositoryImpl implements VeiculoRepository {

    private final JpaVeiculoRepository repository;

    public VeiculoRepositoryImpl(JpaVeiculoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        JpaVeiculoEntity entity = VeiculoMapper.jpaParaDominio(veiculo);
        this.repository.save(entity);
        return VeiculoMapper.jpaParaDominio(entity);
    }

    @Override
    public Optional<Veiculo> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(VeiculoMapper::jpaParaDominio);
    }

    @Override
    public List<Veiculo> buscarTodosVeiculosPorStatusOrdenadoPorPreco(String status) {
        return repository.findByStatusVeiculoOrderByPrecoAsc(status)
                .stream()
                .map(VeiculoMapper::jpaParaDominio)
                .toList();
    }

    @Override
    public JpaVeiculoEntity buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));
    }
}
