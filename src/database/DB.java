package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    // objeto de conexão
    private static Connection conn = null;

    // método para pegar uma conexão ao banco de dados
    public static Connection getConnection() {

        try {

            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            conn = DriverManager.getConnection(url, props);
            return conn;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    // método para carregar as propriedades do banco de dados
    private static Properties loadProperties() {

        try (FileInputStream fs = new FileInputStream("database.properties")){

            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        }
        catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }

    // método para fechar cum conexão com o banco de dados
    public static void closeConnection() {

        if (conn != null){
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    // método para fechar/finalizar uma instância de statement
    public static void closeStatement(Statement statement) {

        if (statement != null){

            try {
                statement.close();
            }
            catch (SQLException e ){
                throw new DBException(e.getMessage());
            }
        }
    }

    //  método para fechar/finalizar uma instância de resultset
    public static void closeResultSet(ResultSet resultSet) {

        if (resultSet != null){

            try {
                resultSet.close();
            }
            catch (SQLException e ){
                throw new DBException(e.getMessage());
            }
        }
    }
}
