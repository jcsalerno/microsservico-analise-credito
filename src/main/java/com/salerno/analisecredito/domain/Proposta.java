package com.salerno.analisecredito.domain;

public class Proposta {


        private Long id;

        private Double valorSolicitado;

        private int prazoPagamento;

        private Boolean aprovada = false;

        private boolean integrada = false;

        private String observacao;

        private Usuario usuario;

        // Construtor padrão (necessário para JPA)
        public Proposta() {
        }

        // Construtor com parâmetros
        public Proposta(Long id, Double valorSolicitado, int prazoPagamento, Boolean aprovada, boolean integrada, String observacao, Usuario usuario) {
            this.id = id;
            this.valorSolicitado = valorSolicitado;
            this.prazoPagamento = prazoPagamento;
            this.aprovada = aprovada != null ? aprovada : false;
            this.integrada = integrada;
            this.observacao = observacao;
            this.usuario = usuario;
        }

        // Getters e Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Double getValorSolicitado() {
            return valorSolicitado;
        }

        public void setValorSolicitado(Double valorSolicitado) {
            this.valorSolicitado = valorSolicitado;
        }

        public int getPrazoPagamento() {
            return prazoPagamento;
        }

        public void setPrazoPagamento(int prazoPagamento) {
            this.prazoPagamento = prazoPagamento;
        }

        public Boolean getAprovada() {
            return aprovada;
        }

        public void setAprovada(Boolean aprovada) {
            this.aprovada = aprovada;
        }

        public boolean isIntegrada() {
            return integrada;
        }

        public void setIntegrada(boolean integrada) {
            this.integrada = integrada;
        }

        public String getObservacao() {
            return observacao;
        }

        public void setObservacao(String observacao) {
            this.observacao = observacao;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public String toString() {
            return "Proposta{" +
                    "id=" + id +
                    ", valorSolicitado=" + valorSolicitado +
                    ", prazoPagamento=" + prazoPagamento +
                    ", aprovada=" + aprovada +
                    ", integrada=" + integrada +
                    ", observacao='" + observacao + '\'' +
                    ", usuario=" +
                    '}';
        }
    }

