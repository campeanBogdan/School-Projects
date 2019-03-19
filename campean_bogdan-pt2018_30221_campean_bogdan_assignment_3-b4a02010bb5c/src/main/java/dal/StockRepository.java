package dal;
import java.beans.PropertyDescriptor; 
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.Stock;
import connection.ConnectionFactory;

public class StockRepository extends GenericRepository<Stock> {
	
	
//	update --------------------------------------------------------
	
	public final String createUpdateQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("update Stock set stock = '" + fields[0] + "' where id = " + fields[1] + ";");
		
		return sb.toString();
	}
	
	
	public final void updateObject(Integer id, String value) {
		Connection connection = null;
		PreparedStatement statement = null;
		String[] fields = new String[10];
		
		fields[0] = new String(value);
		fields[1] = new String(id.toString());
		
		String query = createUpdateQuery(fields);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
//			statement.setInt(1, id);
			System.out.println(statement.toString());
			statement.executeUpdate(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	
// insert ------------------------------------------------------------
	
	public final String createInsertQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("insert into Stock(stock) values ('" + fields[0] + "')");
		
		return sb.toString();
	}
	
	
	public final void insertObject(Stock stock) {
		Connection connection = null;
		PreparedStatement statement = null;
		String[] fields = new String[10];
		
		fields[0] = new String(stock.getStock().toString());
		
		String query = createInsertQuery(fields);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	
	
//	select --------------------------------------------------------
	
	public final Stock selectById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet).get(0);
			
		} catch (Exception e) {
//			System.out.println(e);
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	public final Stock selectByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(name);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet).get(0);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return null;
	}
	
	
	private final List<Stock> createObjects(ResultSet resultSet) {
		List<Stock> list = new LinkedList<Stock>();
		

		try {
			while (resultSet.next()) {
				
				Stock instance = type.newInstance();
				
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}

				list.add(instance);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return list;
	}
}
