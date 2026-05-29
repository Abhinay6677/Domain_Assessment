package com.pharmacy.medicine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Medicine {
	@Id
	private String id;
	private String name;
	private int stock;
	private double price;
	public Medicine() {}
	public Medicine(String id, String name, int stock, double price) {
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
