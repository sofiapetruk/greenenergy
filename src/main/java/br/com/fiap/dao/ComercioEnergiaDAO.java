package br.com.fiap.dao;

import br.com.fiap.to.ComercioEnergiaTO;
import br.com.fiap.to.UnidadeComunidadeTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComercioEnergiaDAO extends Repository {

    public ArrayList<ComercioEnergiaTO> findAll() {
        ArrayList<ComercioEnergiaTO> comercios = new ArrayList<ComercioEnergiaTO>();
        String sql = "SELECT * FROM gs_comercio ORDER BY id_comercio";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();;

            if (rs != null) {
                while (rs.next()) {
                    ComercioEnergiaTO comercio = new ComercioEnergiaTO();

                    comercio.setIdComercio(rs.getLong("id_comercio"));
                    comercio.setIdComunidade(rs.getLong("id_comunidade"));
                    comercio.setUnidadeVedendoraId(rs.getLong("unidade_vendedora_id"));
                    comercio.setUnidadeCompradoraId(rs.getLong("unidade_compradora_id"));
                    comercio.setQuantidade(rs.getDouble("quantidade"));
                    comercio.setDataHora(rs.getDate("data_hora").toLocalDate());
                    comercios.add(comercio);
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return comercios;

    }

    public ComercioEnergiaTO findById(Long idComercio) {
        ComercioEnergiaTO comercio = new ComercioEnergiaTO();
        String sql = "SELECT * FROM gs_comercio WHERE id_comercio = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)){

            ps.setLong(1, idComercio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comercio.setIdComercio(rs.getLong("id_comercio"));
                comercio.setIdComunidade(rs.getLong("id_comunidade"));
                comercio.setUnidadeVedendoraId(rs.getLong("unidade_vendedora_id"));
                comercio.setUnidadeCompradoraId(rs.getLong("unidade_compradora_id"));
                comercio.setQuantidade(rs.getDouble("quantidade"));
                comercio.setDataHora(rs.getDate("data_hora").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return comercio;
    }

    public ComercioEnergiaTO save(ComercioEnergiaTO comercio) {
        String sql = "INSERT INTO gs_comercio(id_comercio, id_comunidade, unidade_vendedora_id, unidade_compradora_id, quantidade) VALUES(gs_seq_id_comercio.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, comercio.getIdComunidade());
            ps.setLong(2, comercio.getUnidadeVedendoraId());
            ps.setLong(3, comercio.getUnidadeCompradoraId());
            ps.setDouble(4, comercio.getQuantidade());

            if (ps.executeUpdate() > 0) {
                return comercio;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public ComercioEnergiaTO update(ComercioEnergiaTO comercio) {
        String sql = "UPDATE gs_comercio SET id_comunidade = ?, unidade_vendedora_id = ?, unidade_compradora_id = ?, quantidade = ? WHERE id_comercio = ? ";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, comercio.getIdComunidade());
            ps.setLong(2, comercio.getUnidadeVedendoraId());
            ps.setLong(3, comercio.getUnidadeCompradoraId());
            ps.setDouble(4, comercio.getQuantidade());
            ps.setLong(5, comercio.getIdComercio());

            if (ps.executeUpdate() > 0) {
                return comercio;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(Long idComercio) {
        String sql = "DELETE FROM gs_comercio WHERE id_comercio = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idComercio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public UnidadeComunidadeTO getUnidadeComunidadeById(Long idUnidade) {
        UnidadeComunidadeTO unidade = null;
        String sql = "SELECT * FROM gs_unidade_comunidade WHERE id_unidade = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUnidade);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                unidade = new UnidadeComunidadeTO();
                unidade.setIdUnidade(rs.getLong("id_unidade"));
                unidade.setNumeroUnidade(rs.getLong("numero_unidade"));
                unidade.setIdComunidade(rs.getLong("id_comunidade"));
                unidade.setNomeUnidade(rs.getString("nome_unidade"));
                unidade.setCapacidadeGeracao(rs.getDouble("capacidade_geracao"));
                unidade.setCapacidadeConsumo(rs.getDouble("capacidade_consumo"));
                unidade.setSaldoEnergia(rs.getDouble("saldo_energia"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta da unidade: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return unidade;
    }


}
