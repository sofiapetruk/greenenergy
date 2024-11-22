package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class UnidadeComunidadeTO {
    private Long idUnidade;
    @NotNull
    private Long numeroUnidade;
    @NotNull
    private Long idComunidade;
    @NotBlank
    private String nomeUnidade;
    @NotNull @PositiveOrZero
    private Double capacidadeGeracao;
    @NotNull @PositiveOrZero
    private Double capacidadeConsumo;
    private Double saldoEnergia;

    //construtor
    public UnidadeComunidadeTO() {
    }

    public UnidadeComunidadeTO(Long idUnidade, @NotNull Long numeroUnidade, @NotNull Long idComunidade, @NotBlank String nomeUnidade, @NotNull @PositiveOrZero Double capacidadeGeracao, @NotNull @PositiveOrZero Double capacidadeConsumo, @NotNull Double saldoEnergia) {

        this.idUnidade = idUnidade;
        this.numeroUnidade = numeroUnidade;
        this.idComunidade = idComunidade;
        this.nomeUnidade = nomeUnidade;
        this.capacidadeGeracao = capacidadeGeracao;
        this.capacidadeConsumo = capacidadeConsumo;
        this.saldoEnergia = saldoEnergia;
    }

    //getters e setters
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

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public Double getCapacidadeGeracao() {
        return capacidadeGeracao;
    }

    public void setCapacidadeGeracao(Double capacidadeGeracao) {
        this.capacidadeGeracao = capacidadeGeracao;
    }

    public Double getCapacidadeConsumo() {
        return capacidadeConsumo;
    }

    public void setCapacidadeConsumo(Double capacidadeConsumo) {
        this.capacidadeConsumo = capacidadeConsumo;
    }

    public Double getSaldoEnergia() {
        return saldoEnergia;
    }

    public void setSaldoEnergia(Double saldoEnergia) {
        this.saldoEnergia = saldoEnergia;
    }

    public Long getNumeroUnidade() {
        return numeroUnidade;
    }

    public void setNumeroUnidade(Long numeroUnidade) {
        this.numeroUnidade = numeroUnidade;
    }


    public void calcularSaldoEnergia() {
        if (this.capacidadeGeracao != null && this.capacidadeConsumo != null) {
            this.saldoEnergia = this.capacidadeGeracao - this.capacidadeConsumo;
        } else {
            this.saldoEnergia = null;
        }
    }

}
