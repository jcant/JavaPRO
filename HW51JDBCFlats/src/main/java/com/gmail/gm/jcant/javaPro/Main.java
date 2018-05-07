package com.gmail.gm.jcant.javaPro;

import java.sql.*;

public class Main {
	public static void main(String[] args) throws SQLException {

		Flats flats = new Flats();

		flats.createFlatsTable();

		fillFlats(flats);

		System.out.println("Flat with id=2:");
		System.out.println(flats.getFlat(2));
		System.out.println("Flats by District=Distr2:");
		printArray(flats.getFlatsByDistrict("Distr2"));

		System.out.println("Flats with price less than 25000");
		printArray(flats.getFlatsPriceLess(25000));

		System.out.println("Flats with price between 20000 and 40000");
		printArray(flats.getFlatsPriceBetween(20000, 40000));

		flats.close();

	}

	private static void printArray(Object[] arr) {
		for (Object element : arr) {
			System.out.println(element);
		}
	}

	private static void fillFlats(Flats flats) {
		flats.addFlat(new Flat("Distr1", "Address1", 60.8, 3, 25500));
		flats.addFlat(new Flat("Distr2", "Address2", 45.2, 1, 15200));
		flats.addFlat(new Flat("Distr3", "Address3", 92.4, 4, 57000));
		flats.addFlat(new Flat("Distr1", "Address4", 32.0, 1, 20540));
		flats.addFlat(new Flat("Distr2", "Address5", 48.1, 2, 29500));
		flats.addFlat(new Flat("Distr3", "Address6", 69.4, 3, 41000));
	}
}
