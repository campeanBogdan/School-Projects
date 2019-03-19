package dal;
import model.Order; 

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import connection.ConnectionFactory;

public class OrderRepository extends GenericRepository<Order> {

	
//	insert ---------------------------------------------------
	
	public final String createInsertQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("insert into warehouseDB.Order(date) values ('");
		sb.append(fields[0]);
		sb.append("');");
		
		return sb.toString();
	}
	
	
	public final void insertObject(Order order) {
		Connection connection = null;
		PreparedStatement statement = null;
		String[] fields = new String[10];
		
		
		fields[0] = new String(order.getDate());
		
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
	
//	select -------------------------------------------------------
	
	public final Order selectById(int id) {
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
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	private final List<Order> createObjects(ResultSet resultSet) {
		List<Order> list = new LinkedList<Order>();
		
		try {
			while (resultSet.next()) {
				
				Order instance = type.newInstance();
				
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
		
		return null;
	}
	
}
