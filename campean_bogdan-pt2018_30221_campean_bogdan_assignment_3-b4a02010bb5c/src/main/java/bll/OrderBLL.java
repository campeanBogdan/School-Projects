package bll;

import java.util.LinkedList;  

import dal.GenericRepository;
import dal.OrderRepository;
import dal.CustomerRepository;
import model.Customer;
import model.Order;
import model.Product;
import model.Stock;


public class OrderBLL {
	
	private GenericRepository<Order> orderRep = new OrderRepository();
	private GenericRepository<Customer> customerRep = new CustomerRepository();

	
//	iau o comanda
	
	public Order getOrder(int id) {
		return (Order) orderRep.selectById(id);
	}
	
	
	
//	iau toate comenzile
	
	public LinkedList<Order> getAllOrders() {
		LinkedList<Order> list = new LinkedList<Order>();
		int i = 1;
		
		while (orderRep.selectById(i) != null) {
			list.add(orderRep.selectById(i));
			i++;
		}
		
		return list;
	}
	
	
//	plasez o comanda
	
	public void addNewOrder(Order order) {
		orderRep.insertObject(order);
	}
	
	
//	iau datele unui client si le afisez intr-un pdf
	
	public void makeNewOrder(LinkedList<Product> productList, LinkedList<Stock> stockList, Customer customer, Order order) {
		PDFDocument pdf = new PDFDocument();
		
		
	// creez un pdf cu datele clientului si toate produsele cumparate + pret, etc
		
		pdf.createPDF(customer, productList, stockList, order);
	}
	
}
