package br.com.fiap.bo;

import br.com.fiap.dao.CadastroComunidadeDAO;
import br.com.fiap.to.CadastroComunidadeTO;

import java.util.ArrayList;

public class CadastroComunidadeBO {

    private CadastroComunidadeDAO cadastroComunidadeDAO;

    public ArrayList<CadastroComunidadeTO> findAll() {
        cadastroComunidadeDAO = new CadastroComunidadeDAO();
        //lógica de programação
        return  cadastroComunidadeDAO.findAll();
    }

    public CadastroComunidadeTO findById(Long idComunidade) {
        cadastroComunidadeDAO = new CadastroComunidadeDAO();
        //lógica de programação
        return  cadastroComunidadeDAO.findById(idComunidade);
    }

    public CadastroComunidadeTO save(CadastroComunidadeTO comunidade) {
        cadastroComunidadeDAO = new CadastroComunidadeDAO();

        //lógica de programação
        return  cadastroComunidadeDAO.save(comunidade);
    }

    public CadastroComunidadeTO update(CadastroComunidadeTO comunidade) {
        cadastroComunidadeDAO = new CadastroComunidadeDAO();

        //lógica de programação
        return  cadastroComunidadeDAO.update(comunidade);
    }

    public boolean delete(Long idComunidade) {
        cadastroComunidadeDAO = new CadastroComunidadeDAO();
        //lógica de programação
        return  cadastroComunidadeDAO.delete(idComunidade);
    }




}
