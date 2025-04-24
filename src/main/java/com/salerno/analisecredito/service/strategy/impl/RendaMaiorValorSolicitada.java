package com.salerno.analisecredito.service.strategy.impl;
import com.salerno.analisecredito.domain.Proposta;
import com.salerno.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class RendaMaiorValorSolicitada implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {

        return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorValorSolicitado(Proposta proposta) {

        return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
    }
}