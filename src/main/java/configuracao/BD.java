package configuracao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BD {

    public static Connection conexao(){

        final Logger LOG = Logger.getLogger("BD.class");

        String driver = "mysql";
        String local = "localhost";
        String nomeBanco = "meuBanco";
        String usuario = "usuario";
        String senha = "senha";

        String url = "jdbc:" + driver + "://" + local + "/" + nomeBanco + "?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC";

        //try resource ao finalizar faz o fechamento da conexao
        try (Connection conn = DriverManager.getConnection(url, usuario, senha)){
            LOG.info("CONECTADO AO BANCO");
            return  conn;
        } catch (SQLException throwables) {
            LOG.info("NAO FOI CONECTADO AO BANCO : " + throwables.getMessage());
        }

        return  null;

    }
}
