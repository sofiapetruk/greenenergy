package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        //lógica de programação
        return usuarioDAO.findAll();
    }

    public UsuarioTO findById(Long idCadastro) {
        usuarioDAO = new UsuarioDAO();
        //lógica de programação
        return usuarioDAO.findById(idCadastro);
    }

    public UsuarioTO save(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        //lógica de programação
        return usuarioDAO.save(usuario);
    }

    public  UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        //lógica de programação
        return usuarioDAO.update(usuario);
    }

    public boolean delete(Long idCadastro) {
        usuarioDAO = new UsuarioDAO();
        //lógica de programação
        return usuarioDAO.delete(idCadastro);
    }

    public boolean validarLogin(String email, String senha) {
        usuarioDAO = new UsuarioDAO();

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser vazio ou nulo.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia ou nula.");
        }

        boolean loginValido = usuarioDAO.validarLogin(email, senha);

        if (!loginValido) {
            throw new IllegalArgumentException("Email ou senha inválidos.");
        }

        return true;
    }


}
