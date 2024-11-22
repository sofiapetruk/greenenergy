package br.com.fiap.bo;

import br.com.fiap.dao.ArmazenamentoDAO;
import br.com.fiap.exception.SaldoEnergiaInsuficienteException;
import br.com.fiap.to.ArmazenamentoTO;
import br.com.fiap.to.CadastroComunidadeTO;
import br.com.fiap.to.ComercioEnergiaTO;
import br.com.fiap.to.UnidadeComunidadeTO;

import java.util.ArrayList;

public class ArmazenamentoBO {

    private ArmazenamentoDAO armazenamentoDAO;

    public ArrayList<ArmazenamentoTO> findAll(){
        armazenamentoDAO = new ArmazenamentoDAO();
        //lógica de programação
        return armazenamentoDAO.findAll();
    }

    public ArmazenamentoTO findById(Long idArmazenamento) {
        armazenamentoDAO = new ArmazenamentoDAO();
        //lógica de programação
        return armazenamentoDAO.findById(idArmazenamento);
    }

    public ArmazenamentoTO save(ArmazenamentoTO armazenamento) {
        armazenamentoDAO = new ArmazenamentoDAO();
        //lógica de programação
        return armazenamentoDAO.save(armazenamento);
    }

    public ArmazenamentoTO update(ArmazenamentoTO armazenamento) {
        armazenamentoDAO = new ArmazenamentoDAO();
        //lógica de programação
        return armazenamentoDAO.update(armazenamento);
    }

    public boolean delete(Long idArmazenamento) {
        armazenamentoDAO = new ArmazenamentoDAO();
        //lógica de programação
        return armazenamentoDAO.delete(idArmazenamento);
    }

}
