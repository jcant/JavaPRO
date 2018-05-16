package com.gmail.gm.jcant.javaPro;

import com.gmail.gm.jcant.javaPro.entities.Account;
import com.gmail.gm.jcant.javaPro.entities.AccountException;
import com.gmail.gm.jcant.javaPro.entities.Client;
import com.gmail.gm.jcant.javaPro.entities.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    synchronized public void convertAmount(Client client, Currency from, Currency to, double value) {
        if ((from == null) || (to == null) || (client == null)) {
            throw new IllegalArgumentException("Null argument!");
        }
        if ((client.getAccount(from) == null) || (client.getAccount(to) == null)) {
            throw new IllegalArgumentException("Convert is not possible: no corresponding currency account!");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Convert value must be greater than 0");
        }

        try {
            em.getTransaction().begin();
            client.getAccount(from).operation(-1 * value);
            double toValue = value * from.getKoef() / to.getKoef();
            client.getAccount(to).operation(toValue);
        } catch (Exception e) {
            em.getTransaction().rollback();
            clients = loadClientsFromBase();
            e.printStackTrace();
        }

    }

    synchronized public void transferAmount(Client from, Client to, Currency curr, double value) {
        if ((from == null) || (to == null) || (curr == null)) {
            throw new IllegalArgumentException("Null argument!");
        }
        if ((from.getAccount(curr) == null) || (to.getAccount(curr) == null)) {
            throw new IllegalArgumentException("Tranfer is not possible: no corresponding currency account!");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Transfer value must be greater than 0");
        }

        try {
            em.getTransaction().begin();
            from.getAccount(curr).operation(-1 * value);
            to.getAccount(curr).operation(value);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            clients = loadClientsFromBase();
            e.printStackTrace();
        }

    }

    public void amountOperation(Client client, Currency curr, double value) {
        if (client == null) {
            throw new IllegalArgumentException("Client is null!");
        }
        if (!clients.contains(client)) {
            throw new IllegalArgumentException("Client is not in clients list!");
        }
        if (curr == null) {
            throw new IllegalArgumentException("Currency is null!");
        }
        if (currency.get(curr.getName()) == null) {
            throw new IllegalArgumentException("Currency is not in currency list!");
        }
        if (client.getAccount(curr) == null) {
            throw new IllegalArgumentException("Client do not have such currency account!");
        }

        try {
            client.getAccount(curr).operation(value);
            persistEntity(client);
        } catch (AccountException e) {
            e.printStackTrace();
        }

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

    public List<Client> getClients() {
        return clients;
    }

    public Client getClient(long id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }

        return null;
    }

    private <T> void persistEntity(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    private List<Client> loadClientsFromBase() {
        try {
            Query query = em.createQuery("SELECT c FROM Client c");
            List<Client> result = query.getResultList();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
