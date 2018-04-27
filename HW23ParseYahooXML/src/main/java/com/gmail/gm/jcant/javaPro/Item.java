package com.gmail.gm.jcant.javaPro;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class Item {

    private Condition condition;

    public Item() {
    }

    public Item(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }
    @XmlElement(namespace = "http://xml.weather.yahoo.com/ns/rss/1.0")
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Item{" +
                "condition=" + condition +
                '}';
    }
}
