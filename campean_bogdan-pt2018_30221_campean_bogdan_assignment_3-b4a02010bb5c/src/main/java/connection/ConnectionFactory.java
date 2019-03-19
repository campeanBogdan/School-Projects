package connection;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;



public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehousedb?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	
	public ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBURL, USER, PASS);
			PreparedStatement ps = conn.prepareStatement("create database warehousedb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DBURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void getCustomer() {
		String str = new String("select * from `customer`");
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(str);
			rs = findStatement.executeQuery();
			rs.next();
			
			System.out.println(rs.getString("idCustomer") + " " + rs.getString("customerName"));
		} catch (Exception r) {
			
		}
	}
	
	
	
}