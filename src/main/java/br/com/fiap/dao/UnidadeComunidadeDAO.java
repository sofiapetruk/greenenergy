package br.com.fiap.dao;

import br.com.fiap.to.UnidadeComunidadeTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UnidadeComunidadeDAO extends Repository{

    public ArrayList<UnidadeComunidadeTO> findAll() {
        ArrayList<UnidadeComunidadeTO> unidades = new ArrayList<UnidadeComunidadeTO>();
        String sql = "SELECT * FROM gs_unidade_comunidade ORDER BY id_unidade";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UnidadeComunidadeTO unidade = new UnidadeComunidadeTO();

                    unidade.setIdUnidade(rs.getLong("id_unidade"));
                    unidade.setIdComunidade(rs.getLong("id_comunidade"));
                    unidade.setNumeroUnidade(rs.getLong("numero_unidade"));
                    unidade.setNomeUnidade(rs.getString("nome_unidade"));
                    unidade.setCapacidadeGeracao(rs.getDouble("capacidade_geracao"));
                    unidade.setCapacidadeConsumo(rs.getDouble("capacidade_consumo"));
                    unidade.calcularSaldoEnergia();
                    unidades.add(unidade);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return unidades;
    }

    public UnidadeComunidadeTO findById(Long idUnidade) {
        UnidadeComunidadeTO unidade = new UnidadeComunidadeTO();
        String sql = "SELECT * FROM gs_unidade_comunidade WHERE id_unidade = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUnidade);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                unidade.setIdUnidade(rs.getLong("id_unidade"));
                unidade.setIdComunidade(rs.getLong("id_comunidade"));
                unidade.setNumeroUnidade(rs.getLong("numero_unidade"));
                unidade.setNomeUnidade(rs.getString("nome_unidade"));
                unidade.setCapacidadeGeracao(rs.getDouble("capacidade_geracao"));
                unidade.setCapacidadeConsumo(rs.getDouble("capacidade_consumo"));
                unidade.calcularSaldoEnergia();
            }
        }  catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return unidade;
    }


    public UnidadeComunidadeTO save(UnidadeComunidadeTO unidade) {
        String sql = "INSERT INTO gs_unidade_comunidade(id_comunidade, nome_unidade, capacidade_geracao, capacidade_consumo) VALUES (?,?,?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, unidade.getIdComunidade());
            ps.setString(2, unidade.getNomeUnidade());


            if (unidade.getCapacidadeGeracao() != null) {
                ps.setDouble(3, unidade.getCapacidadeGeracao());
            } else {
                ps.setNull(3, Types.DOUBLE);
            }

            if (unidade.getCapacidadeConsumo() != null) {
                ps.setDouble(4, unidade.getCapacidadeConsumo());
            } else {
                ps.setNull(4, Types.DOUBLE);
            }


            if (ps.executeUpdate() > 0) {
                return unidade;
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

    public UnidadeComunidadeTO update(UnidadeComunidadeTO unidade) {
        String sql = "UPDATE gs_unidade_comunidade SET id_comunidade  = ?, nome_unidade = ?, capacidade_geracao = ?, capacidade_consumo = ? WHERE id_unidade = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, unidade.getIdComunidade());
            ps.setString(2, unidade.getNomeUnidade());
            ps.setDouble(3, unidade.getCapacidadeGeracao());
            ps.setDouble(4, unidade.getCapacidadeConsumo());
            ps.setLong(5, unidade.getIdUnidade());

            if (ps.executeUpdate() > 0) {
                return unidade;
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

    public boolean delete(Long idUnidade) {
        String sql = "DELETE gs_unidade_comunidade WHERE id_unidade = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUnidade);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;

    }

}
