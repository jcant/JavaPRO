package com.gmail.gm.jcant.javaPro;

import java.util.Random;

public class Main {

	private static Controller controller = new Controller();

	public static void main(String[] args) {

		try {
			fillCurrency();
			addClients();
			
			printClients();
			controller.amountOperation(controller.getClient(1), controller.getCurrency("UAH"), 10000);
			controller.amountOperation(controller.getClient(2), controller.getCurrency("EUR"), 10000);
			controller.amountOperation(controller.getClient(3), controller.getCurrency("USD"), 10000);
			controller.amountOperation(controller.getClient(1), controller.getCurrency("UAH"), -5000);
			controller.amountOperation(controller.getClient(2), controller.getCurrency("EUR"), -5000);
			controller.amountOperation(controller.getClient(3), controller.getCurrency("USD"), -5000);
			printClients();
			
		} finally {
			controller.close();
		}

	}

	private static void printClients() {
		controller.getClients().forEach(cl->System.out.println(cl));
	}
	
	private static void fillCurrency() {
		controller.addCurrency(new Currency("UAH", (double) 1 / 26));
		controller.addCurrency(new Currency("EUR", (double) 1 / 0.9));
		controller.addCurrency(new Currency("USD", 1));
	}

	private static void addClients() {
		Client[] clients = new Client[] { new Client("Ivanov Ivan"), new Client("Petrov Petr"),
				new Client("Sidorov Sidor") };
		Random rd = new Random();
		for (Client client : clients) {
			client.addAccount(new Account(controller.getCurrency("UAH"), rd.nextInt(1000)));
			client.addAccount(new Account(controller.getCurrency("EUR"), rd.nextInt(1000)));
			client.addAccount(new Account(controller.getCurrency("USD"), rd.nextInt(1000)));
			controller.addClient(client);
		}

	}
}
// * Создать базу данных «Банк» с таблицами «Пользователи»,
// «Транзакции», «Счета» и «Курсы валют». Счет бывает 3-х видов:
// USD, EUR, UAH. Написать запросы для пополнения счета в нужной
// валюте, перевода средств с одного счета на другой, конвертации
// валюты по курсу в рамках счетов одного пользователя. Написать
// запрос для получения суммарных средств на счету одного
// пользователя в UAH (расчет по курсу).