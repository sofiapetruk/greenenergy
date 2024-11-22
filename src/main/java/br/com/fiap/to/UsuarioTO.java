package br.com.fiap.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UsuarioTO {
    private Long idCadastro;
    @NotBlank
    private String user;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 8, max = 15)
    private String senha;
    @NotNull
    private LocalDate date;

    //construtores
    public UsuarioTO() {
    }

    public UsuarioTO(Long idCadastro,  @NotBlank String user, @NotBlank @Email String email, @NotBlank @Size(min = 8, max = 15) String senha, @NotNull LocalDate date) {
        this.idCadastro = idCadastro;
        this.user = user;
        this.email = email;
        this.senha = senha;
        this.date = date;
    }

    //getter e setters
    public Long getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(Long idCadastro) {
        this.idCadastro = idCadastro;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
