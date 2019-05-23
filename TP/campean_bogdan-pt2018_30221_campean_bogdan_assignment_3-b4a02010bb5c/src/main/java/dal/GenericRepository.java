package dal;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class GenericRepository<T> {
	
	protected static final Logger LOGGER = Logger.getLogger(GenericRepository.class.getName());
	protected Class<T> type = null;
	
	
	@SuppressWarnings("unchecked")
	public GenericRepository() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	protected String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("select ");
		sb.append(" * ");
		sb.append("from warehouseDB.");
		sb.append(type.getSimpleName());
		sb.append(" where " + field);
		sb.append(" = ?");
		
		return sb.toString();
	}
	
	
	public String createUpdateQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("update Stock set stock = " + fields[0] + " where id = " + fields[1] + ";");
		
		return sb.toString();
	}
	
	
	public void updateObject(Integer id, String value) {
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
			statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	
	
	protected String createInsertQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
//		int i = 0;
//		
//		sb.append("insert into " + type.getSimpleName() + "(");
//		
//		while (i < fields.length && fields[i] != null) {
//			sb.append(fields[i] + ",");
//			i++;
//		}
//		
//		sb.replace(sb.length() - 1, sb.length(), ")");
//		sb.append(" values (?, ?)");
		
		return sb.toString();
	}
	

// insert -----------------------------------------------------------
	
	
	public void insertObject(T obj) {
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		String[] fields = new String[10];
//		String query = createInsertQuery(fields);
//		
//		try {
//			connection = ConnectionFactory.getConnection();
//			statement = connection.prepareStatement(query);
//			resultSet = statement.executeQuery();
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			ConnectionFactory.close(resultSet);
//			ConnectionFactory.close(statement);
//			ConnectionFactory.close(connection);
//		}
	}
	
	
	
// select -----------------------------------------------------------
	
	
	public T selectById(int id) {
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	public T selectByName(String name) {
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
			e.printStackTrace();
		}
		
		return null;
	}
	
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new LinkedList<T>();
		
		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				
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
		
		return null;
	}
	
	
// insert ----------------------------------------------------
	
}
