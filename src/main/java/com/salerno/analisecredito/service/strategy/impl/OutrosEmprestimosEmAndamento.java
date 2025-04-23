package com.salerno.analisecredito.service.strategy.impl;
import com.salerno.analisecredito.domain.Proposta;
import com.salerno.analisecredito.service.strategy.CalculoPonto;
import java.util.Random;

public class OutrosEmprestimosEmAndamento implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return outrosEmprestimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmprestimosEmAndamento() {
        return new Random().nextBoolean();
    }
}
