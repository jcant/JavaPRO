package com.gmail.gm.jcant.javaPro.Entities;

public class Client {
	private int id;
	private String name;
	
	public Client() {
		super();
	}

	public Client(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Client(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}

}
