package br.com.fiap.dao;

import br.com.fiap.to.CadastroComunidadeTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class CadastroComunidadeDAO extends Repository{

    public ArrayList<CadastroComunidadeTO> findAll() {
        ArrayList<CadastroComunidadeTO> comunidades = new ArrayList<CadastroComunidadeTO>();
        String sql = "SELECT * FROM gs_cadastro_comunidades ORDER BY id_comunidade";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CadastroComunidadeTO comunidade = new CadastroComunidadeTO();
                    comunidade.setIdComunidade(rs.getLong("id_comunidade"));
                    comunidade.setTipoComunidade(rs.getString("tipo_comunidade"));
                    comunidade.setEndereco(rs.getString("endereco"));
                    comunidade.setEstado(rs.getString("estado"));
                    comunidade.setTotalEnergia(rs.getDouble("total_energia"));
                    comunidades.add(comunidade);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return comunidades;
    }


    public CadastroComunidadeTO findById(Long idComunidade) {
        CadastroComunidadeTO comunidade = new CadastroComunidadeTO();
        String sql = "SELECT * FROM gs_cadastro_comunidades WHERE id_comunidade = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idComunidade);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comunidade.setIdComunidade(rs.getLong("id_comunidade"));
                comunidade.setTipoComunidade(rs.getString("tipo_comunidade"));
                comunidade.setEndereco(rs.getString("endereco"));
                comunidade.setEstado(rs.getString("estado"));
                comunidade.setTotalEnergia(rs.getDouble("total_energia"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return comunidade;
    }


    public CadastroComunidadeTO save(CadastroComunidadeTO comunidade) {
        String sql = "INSERT INTO gs_cadastro_comunidades(id_comunidade, tipo_comunidade, endereco, estado, total_energia) VALUES (gs_seq_id_comunidade.NEXTVAL,?,?,?,?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, comunidade.getTipoComunidade());
            ps.setString(2, comunidade.getEndereco());
            ps.setString(3, comunidade.getEstado());

            if (comunidade.getTotalEnergia() != null) {
                ps.setDouble(4, comunidade.getTotalEnergia());
            } else {
                ps.setNull(4, Types.DOUBLE);
            }

            if (ps.executeUpdate() > 0) {
                return comunidade;
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


    public CadastroComunidadeTO update(CadastroComunidadeTO comunidade) {
        String sql = "UPDATE gs_cadastro_comunidades SET tipo_comunidade = ?, endereco = ?, estado = ?, total_energia = ? WHERE id_comunidade = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, comunidade.getTipoComunidade());
            ps.setString(2, comunidade.getEndereco());
            ps.setString(3, comunidade.getEstado());

            if (comunidade.getTotalEnergia() != null) {
                ps.setDouble(4, comunidade.getTotalEnergia());
            } else {
                ps.setNull(4, Types.DOUBLE);
            }

            ps.setLong(5, comunidade.getIdComunidade());

            if (ps.executeUpdate() > 0) {
                return comunidade;
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

    public boolean delete(Long idComunidade) {
        String sql = "DELETE FROM gs_cadastro_comunidades WHERE id_comunidade = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idComunidade);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

}
