package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends Repository {

    public ArrayList<UsuarioTO> findAll() {

        ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
        String sql = "SELECT * FROM gs_cadastro ORDER BY id";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO usuario = new UsuarioTO();

                    usuario.setIdCadastro(rs.getLong("id"));
                    usuario.setUser(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setDate(rs.getDate("dt_nascimento").toLocalDate());
                    usuarios.add(usuario);

                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return usuarios;
    }

    public UsuarioTO findById(Long idCadastro) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "SELECT * FROM gs_cadastro WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setLong(1, idCadastro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setIdCadastro(rs.getLong("id"));
                usuario.setUser(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDate(rs.getDate("dt_nascimento").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return usuario;
    }

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO gs_cadastro(id, nome, email, senha, dt_nascimento) VALUES(gs_seq_id_cadastro.NEXTVAL, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getUser());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, java.sql.Date.valueOf(usuario.getDate()));

            if (ps.executeUpdate() > 0) {
                return usuario;
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


    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "UPDATE gs_cadastro SET nome = ?, email = ?, senha = ?, dt_nascimento = ? WHERE id = ? ";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getUser());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, Date.valueOf(usuario.getDate()));
            ps.setLong(5, usuario.getIdCadastro());

            if (ps.executeUpdate() > 0) {
                return usuario;
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

    public boolean delete(Long idCadastro) {
        String sql = "DELETE FROM gs_cadastro WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idCadastro);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT 1 FROM gs_cadastro WHERE email = ? AND senha = ?";
        boolean valido = false;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            valido = rs.next(); // Retorna vdd se encontrar um registro
        } catch (SQLException e) {
            System.out.println("Erro na validação do login: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return valido;
    }





}
