package com.pharmacy.order.dto;

public class MedicineDTO {
	private String id;
	private String name;
	private int stock;
	private double price;
	
	public MedicineDTO() {}
	
	public String getID() { return id; }
	public void setId(String id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getStock() { return stock; }
	public void setStock(int stock) {this.stock = stock; }
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	

}
