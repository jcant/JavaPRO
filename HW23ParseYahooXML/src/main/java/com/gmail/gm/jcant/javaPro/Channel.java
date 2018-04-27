package com.gmail.gm.jcant.javaPro;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="channel")
public class Channel {

    private Item item;

    public Channel() {
    }

    public Channel(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
    @XmlElement
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "item=" + item +
                '}';
    }
}
