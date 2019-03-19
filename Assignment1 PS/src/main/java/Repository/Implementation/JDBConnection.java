package Repository.Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Assignment1";
    private static final String USER = "root";
    private static final String PASS = "bobu";
    private static final int TIMEOUT = 5;

    private Connection connection;
    private String schemaName;

    public JDBConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL + "?useSSL=false", USER, PASS);
            createTables();
            this.schemaName = schemaName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSchemaName() { return this.schemaName; }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS User (" +
                "  id INT(100) NOT NULL AUTO_INCREMENT," +
                "  username VARCHAR(255) NOT NULL, " +
                "  password VARCHAR(255) NOT NULL," +
                "  admin BOOLEAN," +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS Client (" +
                "  id INT(100) NOT NULL AUTO_INCREMENT," +
                "  firstName VARCHAR(255) NOT NULL, " +
                "  lastName VARCHAR(255) NOT NULL," +
                "  cardId INT(100) NOT NULL, " +
                "  CNP VARCHAR(255) NOT NULL, " +
                "  address VARCHAR(255) NOT NULL, " +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS Account (" +
                "  id INT(100) NOT NULL AUTO_INCREMENT," +
                "  type VARCHAR(255) NOT NULL, " +
                "  money INT(100) NOT NULL," +
                "  creationDate VARCHAR(255) NOT NULL, " +
                "  clientId INT(100) NOT NULL, " +
                "  PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS Activity (" +
                " id INT(100) NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(255) NOT NULL, " +
                " date VARCHAR(255) NOT NULL, " +
                " PRIMARY KEY (id)," +
                "  UNIQUE KEY id_UNIQUE (id)" +
                ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        statement.execute(sql);
    }

//    public boolean testConnection() throws SQLException {
//        return connection.isValid(TIMEOUT);
//    }

    public Connection getConnection() {
        return connection;
    }
}