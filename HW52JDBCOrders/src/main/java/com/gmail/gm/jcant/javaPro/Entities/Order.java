package com.gmail.gm.jcant.javaPro.Entities;

public class Order {
	private int id;
	private int idClient;
	private int idProduct;
	
	public Order() {
		super();
	}

	public Order(int id, int idClient, int idProduct) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.idProduct = idProduct;
	}

	public Order(int idClient, int idProduct) {
		super();
		this.idClient = idClient;
		this.idProduct = idProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", idClient=" + idClient + ", idProduct=" + idProduct + "]";
	}
		
}
