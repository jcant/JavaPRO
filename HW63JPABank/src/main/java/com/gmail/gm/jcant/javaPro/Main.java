package com.gmail.gm.jcant.javaPro;

import com.gmail.gm.jcant.javaPro.entities.Account;
import com.gmail.gm.jcant.javaPro.entities.Client;
import com.gmail.gm.jcant.javaPro.entities.ClientException;
import com.gmail.gm.jcant.javaPro.entities.Currency;

import java.util.Random;

public class Main {

	private static Controller controller = new Controller();

	public static void main(String[] args) {
		try {
			fillCurrency();
			addClients();
			
			printClients();
			System.out.println("*** --- ***");
			controller.amountOperation(controller.getClient(1), controller.getCurrency("UAH"), 10000);
			controller.amountOperation(controller.getClient(2), controller.getCurrency("EUR"), 10000);
			controller.amountOperation(controller.getClient(3), controller.getCurrency("USD"), 10000);
			controller.amountOperation(controller.getClient(1), controller.getCurrency("UAH"), -5000);
			controller.amountOperation(controller.getClient(2), controller.getCurrency("EUR"), -5000);
			controller.amountOperation(controller.getClient(3), controller.getCurrency("USD"), -5000);
			printClients();
			System.out.println("*** --- ***");
			controller.transferAmount(controller.getClient(1),controller.getClient(2),controller.getCurrency("UAH"),3000);
			printClients();
			System.out.println("*** --- ***");
			controller.convertAmount(controller.getClient(2),controller.getCurrency("UAH"), controller.getCurrency("USD"),3000);
			printClients();
			System.out.println("*** --- ***");
			Client cl = controller.getClient(1);
			System.out.println("Client "+cl.getName()+" total amount:");
			System.out.println(controller.getClient(1).getTotalAmount(controller.getCurrency("UAH"))+" UAH");
		} catch (ClientException e) {
			e.printStackTrace();
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
			client.addAccount(new Account(controller.getCurrency("UAH"),4000));// rd.nextInt(1000)));
			client.addAccount(new Account(controller.getCurrency("EUR"),4000));//rd.nextInt(1000)));
			client.addAccount(new Account(controller.getCurrency("USD"),4000));//rd.nextInt(1000)));
			controller.addClient(client);
		}

	}
}