package dal;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import connection.ConnectionFactory;
import model.Customer;
import model.Order;


public class CustomerRepository extends GenericRepository<Customer> {

	
//	insert ---------------------------------------------------
	
	public final String createInsertQuery(String[] fields) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("insert into Customer(name, address, telNo) values (");
		sb.append("'" + fields[0] + "', '" + fields[1] + "', '" + fields[2] + "'");
		sb.append(");");
		
		return sb.toString();
	}
	
	public final void insertObject(Customer customer) {
		Connection connection = null;
		PreparedStatement statement = null;
		String[] fields = new String[10];
		
		
		fields[0] = customer.getName();
		fields[1] = customer.getAddress();
		fields[2] = customer.getTelNo();
		
		String query = createInsertQuery(fields);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	
//	select ---------------------------------------------------
	
	public final Customer selectById(int id) {
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
	
	
	private final List<Customer> createObjects(ResultSet resultSet) {
		List<Customer> list = new LinkedList<Customer>();
		
		try {
			while (resultSet.next()) {
				
				Customer instance = type.newInstance();
				
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
