package com.gmail.gm.jcant.javaPro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Currency {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private double koef;

    public Currency() {
        super();
    }

    public Currency(String name, double koef) {
        super();
        this.name = name;
        this.koef = koef;
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

    public double getKoef() {
        return koef;
    }

    public void setKoef(double koef) {
        this.koef = koef;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", koef=" + koef +
                '}';
    }
}
