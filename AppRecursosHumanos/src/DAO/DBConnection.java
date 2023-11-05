
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jenny_izquierdo
 */
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Set the database connection parameters
            DBAtributes params = new DBAtributes();
            
            String jdbcUrl = "jdbc:postgresql://" + params.Params.get(2) + ":" + params.Params.get(4) + "/" + params.Params.get(0);

            // Create the connection
            connection = DriverManager.getConnection(jdbcUrl, params.Params.get(3), params.Params.get(1));
        }
        return connection;
    }

}