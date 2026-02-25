package com.cc.vendas.infraestrutura.global;

import com.cc.vendas.aplicacao.casosdeuso.VendaUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Transactional
public class VendaUseCaseTransactional implements VendaUseCase {

    private final VendaUseCase delegate;

    public VendaUseCaseTransactional(VendaUseCase delegate) {
        this.delegate = delegate;
    }

    @Override
    public void registrarVenda(UUID id, String docComprador) {
        delegate.registrarVenda(id, docComprador);
    }
}