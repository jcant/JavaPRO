package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
		EntityManager em = emf.createEntityManager();

		try {

			fillTable(em, 10);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}

	private static void fillTable(EntityManager em, int num) {
		em.getTransaction().begin();

		List<Menu> menu = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Menu dish = getRandDish();
			dish.setDishName("Dish" + (i + 1));
			menu.add(dish);
		}

		for (Menu dish : menu) {
			em.persist(dish);
		}
		em.getTransaction().commit();
	}

	private static Menu getRandDish() {
		Random rd = new Random();

		return new Menu("name", (double) rd.nextInt(10000) / 100, rd.nextInt(500), (short) rd.nextInt(1));
	}

}
