package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public class ArmazenamentoTO {
    private Long idArmazenamento;
    @NotNull
    private Long idComunidade;
    @NotNull
    private Long idUnidade;
    @NotBlank
    private String tipoGeracao;
    @NotNull
    private Double quantidade;
    private LocalDate dataHora;

    //construtores
    public ArmazenamentoTO() {
    }

    public ArmazenamentoTO(Long idArmazenamento, @NotNull Long idComunidade, @NotNull Long idUnidade, @NotBlank String tipoGeracao, @NotNull Double quantidade, LocalDate dataHora) {
        this.idArmazenamento = idArmazenamento;
        this.idComunidade = idComunidade;
        this.idUnidade = idUnidade;
        this.tipoGeracao = tipoGeracao;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
    }

    //getters e setters

    public Long getIdArmazenamento() {
        return idArmazenamento;
    }

    public void setIdArmazenamento(Long idArmazenamento) {
        this.idArmazenamento = idArmazenamento;
    }

    public LocalDate  getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Long getIdComunidade() {
        return idComunidade;
    }

    public void setIdComunidade(Long idComunidade) {
        this.idComunidade = idComunidade;
    }

    public String getTipoGeracao() {
        return tipoGeracao;
    }

    public void setTipoGeracao(String tipoGeracao) {
        this.tipoGeracao = tipoGeracao;
    }


}
