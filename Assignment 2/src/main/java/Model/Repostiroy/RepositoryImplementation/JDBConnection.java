package Model.Repostiroy.RepositoryImplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ass2";
    private static final String USER = "root";
    private static final String PASS = "bobu";
    private static final int TIMEOUT = 5;

    private Connection connection;
    private String schemaName;

    public JDBConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL + "?useSSL=false", USER, PASS);
            this.schemaName = schemaName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
