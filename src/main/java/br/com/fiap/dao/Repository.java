package br.com.fiap.dao;

import java.sql.Connection;
/*Este código define uma classe abstrata chamada Repository, que representa uma camada de repositório de dados. A ideia é que classes que estendam Repository tenham acesso a métodos para abrir e fechar a conexão com o banco de dados, utilizando a ConnectionFactory. Vamos analisar cada parte do código.*/
public abstract class Repository {
/*Classe Abstrata Repository: como é uma classe abstrata (abstract), ela não pode ser instanciada diretamente. Isso significa que apenas classes que herdam Repository poderão ser criadas. Essa abordagem é comum para definir métodos ou propriedades básicas que outros repositórios específicos podem herdar.
Connection connection: a conexão com o banco de dados é declarada como protected, o que permite que as classes que herdem de Repository também tenham acesso direto a essa conexão.*/
    protected Connection connection;

    public Connection getConnection() {
        try {
            connection = ConnectionFactory.getInstance().getConexao();
            return connection;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
