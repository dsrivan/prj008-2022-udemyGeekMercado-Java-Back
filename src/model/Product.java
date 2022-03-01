package model;

import helper.Utils;

public class Product {
	private static int counter = 1;

	private int id;
	private String name;
	private Double price;

	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
		this.id = Product.counter;
		Product.counter += 1;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Código: "+ this.getId() +
				"\nNome: " + this.getName() +
				"\nPreço: " + Utils.doubleToString(this.getPrice());
	}
}
