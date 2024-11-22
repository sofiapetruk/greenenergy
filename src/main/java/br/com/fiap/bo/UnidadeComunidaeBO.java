package br.com.fiap.bo;

import br.com.fiap.dao.UnidadeComunidadeDAO;
import br.com.fiap.to.UnidadeComunidadeTO;

import java.util.ArrayList;

public class UnidadeComunidaeBO {

    private UnidadeComunidadeDAO unidadeComunidadeDAO;

    public ArrayList<UnidadeComunidadeTO> findAll() {
        unidadeComunidadeDAO = new UnidadeComunidadeDAO();
        //lógica de programação
        return unidadeComunidadeDAO.findAll();
    }

    public UnidadeComunidadeTO findById(Long idUnidade) {
        unidadeComunidadeDAO = new UnidadeComunidadeDAO();
        //lógica de programação
        return unidadeComunidadeDAO.findById(idUnidade);
    }

    public UnidadeComunidadeTO save(UnidadeComunidadeTO unidade) {
        unidadeComunidadeDAO = new UnidadeComunidadeDAO();
        //lógica de programação
        return unidadeComunidadeDAO.save(unidade);
    }

    public UnidadeComunidadeTO update(UnidadeComunidadeTO unidade) {
        unidadeComunidadeDAO = new UnidadeComunidadeDAO();
        //lógica de programação
        return unidadeComunidadeDAO.update(unidade);
    }

    public boolean delete(Long idUnidade) {
        unidadeComunidadeDAO = new UnidadeComunidadeDAO();
        //lógica de programação
        return unidadeComunidadeDAO.delete(idUnidade);
    }

}
