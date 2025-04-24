package com.salerno.analisecredito.listener;
import com.salerno.analisecredito.domain.Proposta;
import com.salerno.analisecredito.service.strategy.AnaliseCreditoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropostaEmAnaliseListener {

    private final AnaliseCreditoService analiseCreditoService;
    private final RabbitTemplate rabbitTemplate;
    private final String exchangePropostaConcluida;

    public PropostaEmAnaliseListener(AnaliseCreditoService analiseCreditoService,
                                     RabbitTemplate rabbitTemplate,
                                     @Value("${rabbitmq.propostaconcluida.exchange}") String exchangePropostaConcluida) {
        this.analiseCreditoService = analiseCreditoService;
        this.rabbitTemplate = rabbitTemplate;
        this.exchangePropostaConcluida = exchangePropostaConcluida;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaEmAnalise(Proposta proposta) {
        analiseCreditoService.analisar(proposta);
        rabbitTemplate.convertAndSend(exchangePropostaConcluida, "", proposta);
    }
}
