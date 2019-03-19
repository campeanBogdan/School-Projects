package model;

public class Product {

	private Integer id;
	private String name;
	private Integer price;
	
	
	public Product() {
		this.id = new Integer(0);
		this.name = new String();
		this.price = new Integer(0);
	}
	
	public Product(String name, Integer price) {
		this.name = name;
		this.price = price;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public Integer getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}
}
