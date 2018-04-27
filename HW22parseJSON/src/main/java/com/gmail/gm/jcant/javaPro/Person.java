package com.gmail.gm.jcant.javaPro;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String surname;
    private List<String> phones = new ArrayList<>();
    private List<URL> sites = new ArrayList<>();
    private Address address;

    public Person() {
        super();
    }

    public Person(String name, String surname, Address address) {
        super();
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public void addPhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone is null");
        }
        phones.add(phone);
    }

    public void addSite(String site) {
        if (site == null) {
            throw new IllegalArgumentException("Site is null");
        }
        try {
            sites.add(new URL(site));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phones=" + phones +
                ", sites=" + sites +
                ", address=" + address +
                '}';
    }
}
