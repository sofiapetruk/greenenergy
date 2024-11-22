package br.com.fiap.bo;

import br.com.fiap.dao.ComercioEnergiaDAO;
import br.com.fiap.exception.SaldoEnergiaInsuficienteException;
import br.com.fiap.to.ComercioEnergiaTO;
import br.com.fiap.to.UnidadeComunidadeTO;

import java.util.ArrayList;

public class ComercioEnergiaBO {

    private ComercioEnergiaDAO comercioEnergiaDAO;

    public ArrayList<ComercioEnergiaTO> findAll() {
        comercioEnergiaDAO = new ComercioEnergiaDAO();
        //lógica de programação
        return comercioEnergiaDAO.findAll();
    }

    public ComercioEnergiaTO findById(Long idComercio) {
        comercioEnergiaDAO = new ComercioEnergiaDAO();
        //lógica de programação
        return comercioEnergiaDAO.findById(idComercio);
    }

    public ComercioEnergiaTO save(ComercioEnergiaTO comercio) {
        comercioEnergiaDAO = new ComercioEnergiaDAO();

        if (!verificarUnidadesVendedoraCompradora(comercio.getUnidadeVedendoraId(), comercio.getUnidadeCompradoraId())) {
            System.out.println("A unidade vendedora e compradora não podem ser a mesma");
            return null;
        }

        UnidadeComunidadeTO unidadeVendedora = comercioEnergiaDAO.getUnidadeComunidadeById(comercio.getUnidadeVedendoraId());

        try {
            realizarComercio(comercio, unidadeVendedora);
        } catch (SaldoEnergiaInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }

        return comercioEnergiaDAO.save(comercio);
    }


    public ComercioEnergiaTO update(ComercioEnergiaTO comercio) {

        comercioEnergiaDAO = new ComercioEnergiaDAO();

        if (!verificarUnidadesVendedoraCompradora(comercio.getUnidadeVedendoraId(), comercio.getUnidadeCompradoraId())) {
            System.out.println("A unidade vendedora e compradora não podem ser a mesma");
            return null;
        }

        UnidadeComunidadeTO unidadeVendedora = comercioEnergiaDAO.getUnidadeComunidadeById(comercio.getUnidadeVedendoraId());

        try {
            realizarComercio(comercio, unidadeVendedora);
        } catch (SaldoEnergiaInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        return comercioEnergiaDAO.update(comercio);
    }

    public boolean delete(Long idComercio) {
        comercioEnergiaDAO = new ComercioEnergiaDAO();
        //lógica de programação
        return comercioEnergiaDAO.delete(idComercio);
    }

    public boolean verificarUnidadesVendedoraCompradora(Long idVendedora, Long idCompradora) {
        return !idVendedora.equals(idCompradora);
    }

    public boolean realizarComercio(ComercioEnergiaTO comercioEnergiaTO, UnidadeComunidadeTO unidadeVedendora) {
        unidadeVedendora.calcularSaldoEnergia();

        if (unidadeVedendora.getSaldoEnergia() < comercioEnergiaTO.getQuantidade()) {

            throw new SaldoEnergiaInsuficienteException("Saldo de energia insuficiente para realizar o comércio.");
        }

        unidadeVedendora.setSaldoEnergia(unidadeVedendora.getSaldoEnergia() - comercioEnergiaTO.getQuantidade());
        return true;
    }

}
