package com.salerno.analisecredito.service.strategy;
import com.salerno.analisecredito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);
}
