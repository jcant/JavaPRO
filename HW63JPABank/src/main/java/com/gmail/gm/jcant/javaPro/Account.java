package com.gmail.gm.jcant.javaPro;

import javax.persistence.*;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currency_id")
	private Currency currency;

	@Column(nullable = false)
	private long amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	public Account() {
		super();
	}

	synchronized public void operation(long delta) {
		if (((amount + delta) < 0)||((amount + delta)>5000)) {
			throw new IllegalArgumentException("Client amount: not enough money!");
		} else {
			amount = amount + delta;
		}
	}

	public Account(Currency currency, long amount) {
		this.currency = currency;
		this.amount = amount;

	}

	public Account(Currency currency, long amount, Client client) {
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (amount != other.amount)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
