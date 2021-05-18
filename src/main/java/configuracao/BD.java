package configuracao;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class BD {

    public static Connection conexao(){

        final Logger LOG = Logger.getLogger("BD.class");

        Connection conn = null;
        try{

            InputStream input = BD.class.getClassLoader().getResourceAsStream("connections.properties");
            Properties props = new Properties();
            props.load(input);

            String driver = props.getProperty("jdbc.driver");
            String local = props.getProperty("db.local");
            String nomeBanco = props.getProperty("db.local");
            String usuario = props.getProperty("db.usuario");
            String senha = props.getProperty("db.senha");

            String url = "jdbc:" + driver + "://" + local + "/" + nomeBanco + "?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC";

            conn = DriverManager.getConnection(url, usuario, senha);


            LOG.info("CONECTADO AO BANCO");

        } catch (SQLException | IOException e) {
            LOG.info("NAO FOI CONECTADO AO BANCO : " + e.getMessage());
        } finally {
            LOG.info("DESCONECTADO DO BANCO");
        }

        return conn;

    }
}
