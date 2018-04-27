package com.gmail.gm.jcant.javaPro;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="results")
public class Results {

    private Channel channel;

    public Results() {
    }

    public Results(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
    @XmlElement
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Results{" +
                "channel=" + channel +
                '}';
    }
}
