package com.salerno.analisecredito.service.strategy.impl;
import com.salerno.analisecredito.constant.MensagemConstante;
import com.salerno.analisecredito.domain.Proposta;
import com.salerno.analisecredito.exceptions.StrategyException;
import com.salerno.analisecredito.service.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;

import java.util.Random;

@Order(1)
public class NomeNegativadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativado()) {
            throw new StrategyException
                    (String.format(MensagemConstante.CLIENTE_NEGATIVADO,proposta.getUsuario().getNome()));
        }
        return 100;
    }

    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }
}
