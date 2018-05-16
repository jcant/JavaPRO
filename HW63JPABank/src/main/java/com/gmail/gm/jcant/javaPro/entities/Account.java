package com.gmail.gm.jcant.javaPro.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(nullable = false)
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Account() {
        super();
    }

    synchronized public void operation(double delta) throws AccountException {
        if ((amount + delta) < 0) {
            throw new AccountException("Client amount: not enough money!");
        } else {
            amount = amount + delta;
        }
    }

    public Account(Currency currency, double amount) {
        this.currency = currency;
        this.amount = amount;

    }

    public Account(Currency currency, double amount, Client client) {
        this.currency = currency;
        this.amount = amount;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Account [id=").append(id);
        builder.append(", currency=").append(currency);
        builder.append(", amount=").append(amount);
        builder.append("]").append(System.lineSeparator());
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Double.compare(account.amount, amount) == 0 &&
                Objects.equals(currency, account.currency) &&
                Objects.equals(client.getId(), account.client.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, currency, amount, client);
    }
}
