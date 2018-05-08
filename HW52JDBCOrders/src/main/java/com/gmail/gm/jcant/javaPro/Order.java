package com.gmail.gm.jcant.javaPro;

public class Order {
	private int id;
	private int id_client;
	private int id_product;
	
	public Order() {
		super();
	}

	public Order(int id, int id_client, int id_product) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.id_product = id_product;
	}

	public Order(int id_client, int id_product) {
		super();
		this.id_client = id_client;
		this.id_product = id_product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", id_client=" + id_client + ", id_product=" + id_product + "]";
	}
		
}
