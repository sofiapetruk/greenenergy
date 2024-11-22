package br.com.fiap.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/*Este código implementa uma ConnectionFactory em Java, que é uma fábrica de conexões para um banco de dados.
Ele utiliza o padrão Singleton para garantir que apenas uma instância da conexão seja criada durante o tempo de
execução da aplicação*/

/*Em Java, as variáveis e métodos que são marcados como static são como o sino da escola. Eles não pertencem a uma sala ou criança específica (ou seja, a um objeto eespecífico). Em vez disso, eles pertencem a toda a escola (classe).Todo mundo na escola pode acessar o sino e usá-lo, e ele não muda de sala em sala ou de criança em criança.
Então, quando criamos algo como uma variável ou método static em Java, significa que ele é o mesmo para todos os objetos daquela classe. Se mudamos o valor de uma variável static, todos os objetos dessa classe veem essa mudança, como todos na escola que ouvem o mesmo sino tocar.
Isso é útil, por exemplo, para contar quantas crianças existem na escola. Podemos usar uma variável static para isso, pois não queremos que cada sala conte suas crianças sozinha; queremos que o número total de crianças seja o mesmo para todos.*/
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection conexao;
    private String url;
    private String user;
    private String pass;
    private String driver;

    //construtor
    public ConnectionFactory(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    public  static ConnectionFactory getInstance() {
        ConnectionFactory result = instance;

        if (result != null) {
            return result;
        }

        Properties prop = new Properties();  //Cria um objeto para Properties para carregar a propriedades do arquivo application.properties, que contém as config da conexao banco
        FileInputStream file = null; // carrega o arquivo application, que deve conter as chaves datasource

        try {
            file = new FileInputStream("./src/main/resources/application.properties");
            prop.load(file);

            String url = prop.getProperty("datasource.url");
            String user = prop.getProperty("datasource.username");
            String pass = prop.getProperty("datasource.password");
            String driver = prop.getProperty("datasource.driver-class-name");
            file.close();

            if (instance == null) {
                instance = new ConnectionFactory(url, user, pass, driver);
            }
            return  instance;

            /*Ler o arquivo de propriedades: o arquivo application.properties é aberto e carregado usando Properties, que lê as configurações do banco de dados(URL, usuário, senha e driver).
             prop.getProperty(...): obtém o valor de cada chave (URL, usuário, senha e driver) para configurar a conexão.
             */

        } catch (FileNotFoundException e) {
            System.out.printf("Erro (FileNotFoundException): " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro (IOExeption): " + e.getMessage());
        }
        return  null;
    }

    public Connection getConexao() {
        try {
            if (this.conexao != null && !this.conexao.isClosed()) {
                return this.conexao;
            }
            if (this.getDriver() == null || this.getDriver().isEmpty()) {
                throw new ClassNotFoundException("Nome da classe nulo ou em branco");
            }
            if (this.getUrl() == null || this.getUrl().isEmpty()) {
                throw new ClassNotFoundException("URL de conexão nulo ou em branco");
            }
            if (this.getUser() == null || this.getUser().isEmpty()) {
                throw new ClassNotFoundException("Usuário nulo ou em branco");
            }

            Class.forName(this.getDriver()); //Carrega o driver: o método Class.forName(this.getDriver()) carrega a classe do driver JDBC. Se isso falhar, uma ClassNotFoundException é lançada.
            this.conexao = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPass()); //DriverManager.getConnection() usa a URL, o usuário e a senha para estabelecer a conexão com o banco de dados.

        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar a classe do driver: " + e.getMessage());
            System.exit(1);
        }

        return conexao;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }
}
