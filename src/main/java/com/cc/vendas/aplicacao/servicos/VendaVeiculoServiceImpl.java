package com.cc.vendas.aplicacao.servicos;

import com.cc.vendas.aplicacao.casosdeuso.VendaUseCase;
import com.cc.vendas.dominio.veiculo.VeiculoRepository;
import com.cc.vendas.dominio.venda.VendaVeiculo;
import org.springframework.stereotype.Service;

@Service
public class VendaVeiculoServiceImpl implements VendaUseCase {

    private final VeiculoRepository repositoryPort;

    public VendaVeiculoServiceImpl(VeiculoRepository repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public VendaVeiculo venda(VendaVeiculo vendaVeiculo) {
        return null;
    }
}
