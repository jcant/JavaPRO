package com.gmail.gm.jcant.javaPro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Menu {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String dishName;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private int weight;

	@Column(nullable = false)
	private short discount;

	public Menu() {
		super();
	}

	public Menu(String dishName, double price, int weight, short discount) {
		super();
		this.dishName = dishName;
		this.price = price;
		this.weight = weight;
		this.discount = discount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public short isDiscount() {
		return discount;
	}

	public void setDiscount(short discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [");
		builder.append("id=").append(id);
		builder.append(", dishName=").append(dishName);
		builder.append(", price=").append(price);
		builder.append(", weight=").append(weight);
		builder.append(", discount=").append(discount);
		builder.append("]");
		return builder.toString();
	}

}

// Создать таблицу «Меню в ресторане». Колонки: название блюда,
// его стоимость, вес, наличие скидки. Написать код для
// добавления записей в таблицу и их выборки по критериям
// «стоимость от-до», «только со скидкой», выбрать набор блюд
// так, чтобы их суммарный вес был не более 1 КГ.