package br.com.fiap.dao;

import br.com.fiap.to.ArmazenamentoTO;

import java.sql.*;
import java.util.ArrayList;

public class ArmazenamentoDAO extends Repository {

    public ArrayList<ArmazenamentoTO> findAll() {
        ArrayList<ArmazenamentoTO> armazenamentos = new ArrayList<ArmazenamentoTO>();
        String sql = "SELECT * FROM gs_armazenamento ORDER BY id_amarzenamento";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    ArmazenamentoTO armazenamento = new ArmazenamentoTO();

                    armazenamento.setIdArmazenamento(rs.getLong("id_amarzenamento"));
                    armazenamento.setIdComunidade(rs.getLong("id_comunidade"));
                    armazenamento.setIdUnidade(rs.getLong("id_unidade"));
                    armazenamento.setTipoGeracao(rs.getString("tipo_geraçao"));
                    armazenamento.setQuantidade(rs.getDouble("quantidade"));
                    armazenamento.setDataHora(rs.getDate("data_hora").toLocalDate());
                    armazenamentos.add(armazenamento);

                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return armazenamentos;
    }

    public ArmazenamentoTO findById(Long idArmazenamento) {
        ArmazenamentoTO armazenamento = new ArmazenamentoTO();
        String sql = "SELECT * FROM gs_armazenamento WHERE id_amarzenamento = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setLong(1, idArmazenamento);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                armazenamento.setIdArmazenamento(rs.getLong("id_amarzenamento"));
                armazenamento.setIdComunidade(rs.getLong("id_comunidade"));
                armazenamento.setIdUnidade(rs.getLong("id_unidade"));
                armazenamento.setTipoGeracao(rs.getString("tipo_geraçao"));
                armazenamento.setQuantidade(rs.getDouble("quantidade"));
                armazenamento.setDataHora(rs.getDate("data_hora").toLocalDate());
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return armazenamento;
    }

    public ArmazenamentoTO save(ArmazenamentoTO armazenamento) {
        String sql = "INSERT INTO gs_armazenamento (id_amarzenamento, id_comunidade, id_unidade, tipo_geraçao, quantidade) VALUES (gs_seq_id_armazenamento.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, armazenamento.getIdComunidade());
            ps.setLong(2, armazenamento.getIdUnidade());
            ps.setString(3, armazenamento.getTipoGeracao());
            ps.setDouble(4, armazenamento.getQuantidade());

            if (ps.executeUpdate() > 0) {
                return armazenamento;
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




    public ArmazenamentoTO update(ArmazenamentoTO armazenamento) {
        String sql = "UPDATE gs_armazenamento SET id_comunidade = ?, id_unidade = ?, tipo_geraçao = ?, quantidade = ? WHERE id_amarzenamento = ? ";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, armazenamento.getIdComunidade());
            ps.setLong(2, armazenamento.getIdUnidade());
            ps.setString(3, armazenamento.getTipoGeracao());
            ps.setDouble(4, armazenamento.getQuantidade());
            ps.setLong(5, armazenamento.getIdArmazenamento());

            if (ps.executeUpdate() > 0) {
                return armazenamento;
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



    public boolean delete(Long idArmazenamento) {
        String sql = "DELETE FROM gs_armazenamento WHERE id_amarzenamento = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1,  idArmazenamento);
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;

    }


}
