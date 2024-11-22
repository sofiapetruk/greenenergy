package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CadastroComunidadeTO {
    private Long idComunidade;
    @NotBlank
    private String tipoComunidade;
    @NotBlank
    private String endereco;
    @NotBlank
    private String estado;

    private Double totalEnergia;

    //construtor
    public CadastroComunidadeTO() {
    }

    public CadastroComunidadeTO(Long idComunidade, @NotBlank String tipoComunidade, @NotBlank String endereco, @NotBlank String estado, Double totalEnergia) {
        this.idComunidade = idComunidade;
        this.tipoComunidade = tipoComunidade;
        this.endereco = endereco;
        this.estado = estado;
        this.totalEnergia = totalEnergia;
    }

    //getters e setters
    public Long getIdComunidade() {
        return idComunidade;
    }

    public void setIdComunidade(Long idComunidade) {
        this.idComunidade = idComunidade;
    }

    public String getTipoComunidade() {
        return tipoComunidade;
    }

    public void setTipoComunidade(String tipoComunidade) {
        this.tipoComunidade = tipoComunidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotalEnergia() {
        return totalEnergia;
    }

    public void setTotalEnergia(Double totalEnergia) {
        this.totalEnergia = totalEnergia;
    }
}
