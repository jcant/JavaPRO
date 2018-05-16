package com.gmail.gm.jcant.javaPro.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(String name) {
        super();
        this.name = name;
    }

    public Account getAccount(Currency curr) {
    	for (Account account : accounts) {
			if(account.getCurrency().equals(curr)) {
				return account;
			}
		}
    	
    	return null;
    }
    public void addAccount(Account acc){
        acc.setClient(this);
        accounts.add(acc);
    }

    public double getTotalAmount(Currency base) throws ClientException{
    	if(accounts.size() == 0){
    		throw new ClientException("Client does not have accounts!");
		}
		if (getAccount(base)==null){
    		throw new ClientException("Client does not have such base currency");
		}

    	double total = 0;
		for (Account acc: accounts) {
			total += acc.getAmount() * acc.getCurrency().getKoef();
		}
		total = total / base.getKoef();
		return total;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client: id=").append(id).append(", name=").append(name).append(System.lineSeparator());
		builder.append(accounts).append(System.lineSeparator());
		builder.append(" ---end client info--- ").append(System.lineSeparator());
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Client other = (Client) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
    
}
