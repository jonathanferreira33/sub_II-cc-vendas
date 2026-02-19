package com.cc.vendas.dominio.pagamento;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository {

    Pagamento cadastrarPagamento(Pagamento pagamento);
    Optional<Pagamento> buscarPagamentoPorId(UUID id);
}
