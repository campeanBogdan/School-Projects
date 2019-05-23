package model;

import java.beans.Transient;
import java.util.LinkedList;


public class Order {

	private transient LinkedList<Product> productList;
	
	private Integer id;
	private String date;
	
	
	public Order() {
//		this.id = new Integer(0);
		this.date = new String();
		this.productList = new LinkedList<Product>();
	}
	
	public Order(String date) {
		this.date = date;
		this.productList = new LinkedList<Product>();
	}
	
	public LinkedList<Product> getAllProducts() {
		return productList;
	}
	
	public void addProduct(Product product) {
		productList.add(product);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
}
