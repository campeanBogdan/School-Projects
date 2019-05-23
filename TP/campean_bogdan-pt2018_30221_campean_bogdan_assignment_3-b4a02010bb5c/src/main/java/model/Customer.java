package model;

public class Customer {
	
	private Integer id;
	private String name;
	private String address;
	private String telNo;
	
	
	public Customer() {
		this.id = new Integer(0);
		this.name = new String();
		this.address = new String();
		this.telNo = new String();
	}
	
	public Customer(String name, String address, String telNo) {
		this.name = name;
		this.address = address;
		this.telNo = telNo;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getTelNo() {
		return telNo;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
}
