package com.cc.vendas.aplicacao.servicos;

import com.cc.vendas.adaptadores.saida.entidades.JpaVeiculoEntity;
import com.cc.vendas.adaptadores.saida.persistencia.mapper.VeiculoMapper;
import com.cc.vendas.adaptadores.saida.persistencia.repositorios.VeiculoRepositoryImpl;
import com.cc.vendas.aplicacao.casosdeuso.VeiculoUseCase;
import com.cc.vendas.aplicacao.dto.VeiculoDTO;
import com.cc.vendas.dominio.veiculo.StatusVeiculo;
import com.cc.vendas.dominio.veiculo.Veiculo;
import com.cc.vendas.dominio.veiculo.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VeiculoServiceImpl implements VeiculoUseCase {

    private final VeiculoRepository repository;

    public VeiculoServiceImpl(VeiculoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Veiculo atualizarDadosVeiculo(UUID id, VeiculoDTO veiculoDTO) {

        JpaVeiculoEntity entidade = repository.buscarEntidadePorId(id);

        entidade.setAno(veiculoDTO.getAno());
        entidade.setDocComprador(veiculoDTO.getDocComprador());
        entidade.setMarca(veiculoDTO.getMarca());
        entidade.setModelo(veiculoDTO.getModelo());
        entidade.setPreco(veiculoDTO.getPreco());
        entidade.setStatusVeiculo(veiculoDTO.getStatus().name());

        return VeiculoMapper.jpaParaDominio(entidade);
    }

    @Override
    public Optional<Veiculo> buscarVeiculoPorId(UUID id) {
        return repository.buscarPorId(id);
    }

    @Override
    public List<Veiculo> buscarVeiculosDisponiveis() {
        return repository.buscarTodosVeiculosPorStatusOrdenadoPorPreco(StatusVeiculo.DISPONIVEL_PARA_VENDA.name());
    }

    @Override
    public List<Veiculo> buscarVeiculosVendidos() {
        return repository.buscarTodosVeiculosPorStatusOrdenadoPorPreco(StatusVeiculo.VENDIDO.name());
    }
}
