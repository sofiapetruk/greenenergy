package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.sql.Date;
import java.time.LocalDate;

public class ComercioEnergiaTO {
    private Long idComercio;
    @NotNull
    private Long idComunidade;
    @NotNull
    private Long unidadeVedendoraId;
    @NotNull
    private Long unidadeCompradoraId;
    @NotNull @PositiveOrZero
    private Double quantidade;
    private LocalDate dataHora;

    private Double saldoVendedor;
    private Double saldoComprador;

    //construtores
    public ComercioEnergiaTO() {
    }

    public ComercioEnergiaTO(Long idComercio, @NotNull Long idComunidade, @NotNull Long unidadeVedendoraId, @NotNull Long unidadeCompradoraId, @NotNull @PositiveOrZero  Double quantidade, LocalDate dataHora, Double saldoVendedor, Double saldoComprador) {
        this.idComercio = idComercio;
        this.idComunidade = idComunidade;
        this.unidadeVedendoraId = unidadeVedendoraId;
        this.unidadeCompradoraId = unidadeCompradoraId;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.saldoVendedor = saldoVendedor;
        this.saldoComprador = saldoComprador;
    }

    //getters e setters
    public Long getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Long idComercio) {
        this.idComercio = idComercio;
    }

    public Long getUnidadeVedendoraId() {
        return unidadeVedendoraId;
    }

    public void setUnidadeVedendoraId(Long unidadeVedendoraId) {
        this.unidadeVedendoraId = unidadeVedendoraId;
    }

    public Long getUnidadeCompradoraId() {
        return unidadeCompradoraId;
    }

    public void setUnidadeCompradoraId(Long unidadeCompradoraId) {
        this.unidadeCompradoraId = unidadeCompradoraId;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public Long getIdComunidade() {
        return idComunidade;
    }

    public void setIdComunidade(Long idComunidade) {
        this.idComunidade = idComunidade;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSaldoVendedor() {
        return saldoVendedor;
    }

    public void setSaldoVendedor(Double saldoVendedor) {
        this.saldoVendedor = saldoVendedor;
    }

    public Double getSaldoComprador() {
        return saldoComprador;
    }

    public void setSaldoComprador(Double saldoComprador) {
        this.saldoComprador = saldoComprador;
    }
}
