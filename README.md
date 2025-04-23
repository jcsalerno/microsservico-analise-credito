# Microsserviço de Análise de Crédito

Este projeto é o **terceiro microsserviço** de um sistema de análise de crédito distribuído com arquitetura orientada a mensagens utilizando RabbitMQ.

Ele é responsável por **avaliar propostas de crédito**, atribuindo pontuação com base em regras de negócio, e decidir se a proposta será aprovada ou não. Em seguida, ele envia a resposta para o microsserviço de notificação.

---

## 📦 Microsserviços relacionados

1. [Microsserviço de Propostas](https://github.com/jcsalerno/microsservico-proposta) – Criação de propostas
2. [Microsserviço de Notificações](https://github.com/jcsalerno/microsservico-notificacao) – Envia notificações após análise
3. **Microsserviço de Análise de Crédito** – _este projeto_

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring AMQP
- RabbitMQ
- Strategy Pattern

---

## 📊 Funcionalidade

Este serviço ouve uma fila RabbitMQ chamada `proposta.pendente` e realiza a análise de crédito baseada em múltiplos critérios, aplicando as estratégias:

1. **Nome negativado**
2. **Outros empréstimos em andamento**
3. **Pontuação Serasa (score simulado)**

Se a pontuação acumulada for maior que 350 pontos, a proposta é aprovada. Caso contrário, ela será recusada com uma observação explicando o motivo.

---

## 🧪 Exemplo de JSON de proposta recebida

```json
{
  "nome": "Nami",
  "sobrenome": "Cesar",
  "telefone": "(55) 99175-9423",
  "cpf": "148.899.747-01",
  "renda": 1000.00,
  "valorSolicitado": 5000.00,
  "prazoPagamento": 12
}
```
## 🧠 Estratégias implementadas (padrão Strategy)

- `NomeNegativadoImpl`: verifica se o cliente possui nome negativado (simulação).
- `OutrosEmprestimosEmAndamento`: simula se o cliente tem outros empréstimos.
- `PontuacaoScoreImpl`: simula a pontuação de crédito e avalia a faixa.
- `PrazoPagamentoInferirDezAnos` : simula se o prazor é menor que 10 anos.
- `RendarMaiorValorSolicitado` : verifica se a renda do cliente é maior que o valor solicitado.

Cada implementação retorna uma pontuação que influencia a decisão final.

---

## 💠 Configuração

Este serviço depende das seguintes propriedades no `application.properties`:

```properties
rabbitmq.queue.proposta.pendente=proposta.pendente
rabbitmq.propostaconcluida.exchange=proposta.concluida
```

---

## 🧵 Fluxo de funcionamento

1. Proposta é criada no microsserviço de propostas.
2. Ela é enviada à fila `proposta.pendente`.
3. O microsserviço de análise consome a proposta e executa as regras.
4. A decisão (aprovada ou não) é enviada ao exchange `proposta.concluida`.

---

## ⚠️ Tratamento de erros

Casos como cliente negativado ou pontuação muito baixa geram exceções específicas (`StrategyException`), que são capturadas e transformadas em observações na proposta recusada.

---