package com.example.models.daos;

import com.example.models.Pagamento;
import com.example.utils.Resultado;

public interface PagamentoDAO {
    Resultado<Pagamento> registrar(Pagamento pagamento); 
}
