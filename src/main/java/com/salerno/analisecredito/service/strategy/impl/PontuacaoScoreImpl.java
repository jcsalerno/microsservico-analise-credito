package com.salerno.analisecredito.service.strategy.impl;
import com.salerno.analisecredito.constant.MensagemConstante;
import com.salerno.analisecredito.domain.Proposta;
import com.salerno.analisecredito.exceptions.StrategyException;
import com.salerno.analisecredito.service.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Random;

@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {

        int score = score();

        if (score < 200) {
            throw new StrategyException
                    (String.format(MensagemConstante.PONTUACAO_SERASA_BAIXA,proposta.getUsuario().getNome()));
        }

        return switch (getFaixaScore(score)) {
            case 1 -> 150;
            case 2 -> 180;
            default -> 220;
        };
    }

    private int getFaixaScore(int score) {
        if (score <= 400) return 1;
        if (score <= 600) return 2;
        return 3;
    }

    private int score() {
        return new Random().nextInt(0, 1000);
    }
}
