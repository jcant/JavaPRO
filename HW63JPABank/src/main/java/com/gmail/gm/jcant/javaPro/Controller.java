package com.gmail.gm.jcant.javaPro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controller {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPABank");
	
	private EntityManager em = emf.createEntityManager();

	private Map<String, Currency> currency = new HashMap<>();
	private List<Client> clients = new ArrayList<>();

	public Controller() {
		super();
	}

	public void close() {
		emf.close();
	}

	public void transferAmount(Client from, Client to, Currency curr, long value) {
		if((from==null)||(to==null)||(curr==null)) {
			throw new IllegalArgumentException("Null argument!");
		}
		if((from.getAccount(curr)==null)||(to.getAccount(curr)==null)) {
			throw new IllegalArgumentException("Tranfer is not possible: no corresponding currency account!");
		}
		if(value <= 0) {
			throw new IllegalArgumentException("Transfer value must be greater than 0");
		}
		
		em.getTransaction().begin();
		
	}
	
	public void amountOperation(Client client, Currency curr, long value) {
		if (client == null) {
			throw new IllegalArgumentException("Client is null!");
		}
		if (!clients.contains(client)) {
			throw new IllegalArgumentException("Client is not in clients list!");
		}
		if (curr == null) {
			throw new IllegalArgumentException("Currency is null!");
		}
		if (currency.get(curr.getName())==null) {
			throw new IllegalArgumentException("Currency is not in currency list!");
		}
		if (client.getAccount(curr)==null) {
			throw new IllegalArgumentException("Client do not have such currency account!");
		}
		
		client.getAccount(curr).operation(value);
		persistEntity(client);
			
	}
	
	
	public void addAccount(Client client, Account account) {
		client.addAccount(account);
	}
	
	public void addCurrency(Currency curr) {
		if (!currency.values().contains(curr)) {
			currency.put(curr.getName(), curr);
			persistEntity(curr);
		}
	}
	
	public Currency getCurrency(String name) {
		return currency.get(name);
	}

	public void addClient(Client client) {
		if (!clients.contains(client)) {
			clients.add(client);

			persistEntity(client);
		}
	}
	
	public List<Client> getClients(){
		return clients;
	}
	
	public Client getClient(long id) {
		for (Client client : clients) {
			if(client.getId() == id) {
				return client;
			}
		}
		
		return null;
	}

	private <T> void persistEntity(T entity) {
		//EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		//finally {
		//	em.close();
		//}
	}

}
