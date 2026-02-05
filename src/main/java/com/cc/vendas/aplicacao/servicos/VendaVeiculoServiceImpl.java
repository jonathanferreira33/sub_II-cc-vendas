package com.cc.vendas.aplicacao.servicos;

import com.cc.vendas.aplicacao.casosdeuso.VendaUseCase;
import com.cc.vendas.dominio.excecao.RegraNegocioException;
import com.cc.vendas.dominio.veiculo.Veiculo;
import com.cc.vendas.dominio.veiculo.VeiculoRepository;
import com.cc.vendas.dominio.venda.VendaVeiculo;
import com.cc.vendas.dominio.venda.VendaVeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VendaVeiculoServiceImpl implements VendaUseCase {

    private final VeiculoRepository veiculoRepositoryPort;
    private final VendaVeiculoRepository vendaRepositoryPort;


    public VendaVeiculoServiceImpl(VeiculoRepository veiculoRepositoryPort, VendaVeiculoRepository vendaRepositoryPort) {
        this.veiculoRepositoryPort = veiculoRepositoryPort;
        this.vendaRepositoryPort = vendaRepositoryPort;
    }

    @Override
    @Transactional
    public void registrarVenda(UUID id, String docComprador) {
        Veiculo veiculo = veiculoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new RegraNegocioException("Veículo não encontrado"));

        veiculo.registrarVenda(docComprador);

        veiculoRepositoryPort.salvar(veiculo);

        VendaVeiculo venda = VendaVeiculo.criar(
                veiculo.getId(),
                veiculo.getPreco(),
                docComprador
        );

        vendaRepositoryPort.salvar(venda);
    }
}
