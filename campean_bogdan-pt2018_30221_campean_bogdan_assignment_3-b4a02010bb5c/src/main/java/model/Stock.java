package model;

public class Stock {
	
	private Integer id;
	private Integer stock;
	
	
	public Stock() {
		this.stock = new Integer(0);
		setStock(0);
	}
	
	public Stock(Integer stock) {
		this.stock = stock;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void updateStock(Integer stock) {
		this.stock += stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
