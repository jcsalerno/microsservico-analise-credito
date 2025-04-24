package com.salerno.analisecredito.service.strategy;
import com.salerno.analisecredito.domain.Proposta;
import com.salerno.analisecredito.exceptions.StrategyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AnaliseCreditoService {

    @Autowired
    private List<CalculoPonto> calculoPontoList;

    @Autowired
    private NotificacaoRabbitMQService notificacaoRabbitMQService;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String exchangePropostaConcluida;

    public void analisar(Proposta proposta) {
            try {
                int pontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
                proposta.setAprovada(pontos > 350);
            } catch (StrategyException ex) {
                proposta.setAprovada(false);
                proposta.setObservacao(ex.getMessage());
            }
           notificacaoRabbitMQService.notificar(exchangePropostaConcluida, proposta);
        }
    }
