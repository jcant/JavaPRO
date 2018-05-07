package com.gmail.gm.jcant.javaPro;

public class Flat {
	
	private int id;
	private String district;
	private String address;
	private double area;
	private int rooms;
	private int price;
	
	public Flat() {
		super();
	}

	public Flat(String district, String address, double area, int rooms, int price) {
		super();
		this.district = district;
		this.address = address;
		this.area = area;
		this.rooms = rooms;
		this.price = price;
	}
	
	public Flat(int id, String district, String address, double area, int rooms, int price) {
		super();
		this.id = id;
		this.district = district;
		this.address = address;
		this.area = area;
		this.rooms = rooms;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flat [id=" + id + ", district=" + district + ", address=" + address + ", area=" + area + ", rooms="
				+ rooms + ", price=" + price + "]";
	}

}
