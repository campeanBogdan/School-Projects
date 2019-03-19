package dal;
import connection.ConnectionFactory; 
import model.Product;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class ProductRepository extends GenericRepository<Product> {

	
//	update -----------------------------------------------------
	
	public final String createUpdateQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("update Product set price = '" + fields[0] + "' where id = " + fields[1] + ";");
		
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
	
	
//	select ------------------------------------------------------
	
	public final Product selectByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("name");
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet).get(0);
		} catch (Exception e) {
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return null;
	}
	
	
	public final Product selectById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			try {
				return createObjects(resultSet).get(0);
			} catch (Exception e) {
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	private final List<Product> createObjects(ResultSet resultSet) {
		List<Product> list = new LinkedList<Product>();
		
		try {
			while (resultSet.next()) {
				
				Product instance = type.newInstance();
				
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
		}
		
		return list;
	}
	
// insert ---------------------------------------------------------------
	
	@Override
	public final String createInsertQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("insert into Product(name, price) values ('" + fields[0] + "'," + fields[1] + ")");
		
		return sb.toString();
	}
	
	
	@Override
	public void insertObject(Product product) {
		Connection connection = null;
		PreparedStatement statement = null;
		String[] fields = new String[10];
		
		
		fields[0] = new String(product.getName());
		fields[1] = new String(product.getPrice().toString());
		
		String query = createInsertQuery(fields);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			
			System.out.println(statement);
			statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
}
