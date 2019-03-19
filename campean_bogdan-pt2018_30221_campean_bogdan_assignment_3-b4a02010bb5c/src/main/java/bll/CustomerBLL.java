package bll;

import java.util.*;

import dal.CustomerRepository;
import dal.GenericRepository;
import model.Customer;

public class CustomerBLL {
	
	private GenericRepository<Customer> customerRep = new CustomerRepository();
	
	
//	afisez toti clientii
	
	public LinkedList<Customer> getAllCustomers() {
		int i = 1;
		LinkedList<Customer> list = new LinkedList<Customer>();
		
		while (customerRep.selectById(i) != null) {
			list.add((Customer) customerRep.selectById(i));
			i++;
		}
		
		return list;
	}
	
	
//	afisez un client (pt. afisarea comenzii in pdf)
	
	protected Customer getCustomer(int id) {
		return (Customer) customerRep.selectById(id);
	}
	
	
//	adaug un client
	
	public void addNewCustomer(Customer customer) {
		customerRep.insertObject(customer);
	}
}
