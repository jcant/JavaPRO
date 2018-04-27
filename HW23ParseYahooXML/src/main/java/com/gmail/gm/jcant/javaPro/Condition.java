package com.gmail.gm.jcant.javaPro;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://xml.weather.yahoo.com/ns/rss/1.0", name="condition")
public class Condition {

    private int code;

    private String date;

    private int temp;

    private String text;

    public Condition() {
    }

    public Condition(int code, String date, int temp, String text) {
        this.code = code;
        this.date = date;
        this.temp = temp;
        this.text = text;
    }

    public int getCode() {
        return code;
    }
    @XmlAttribute
    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }
    @XmlAttribute
    public void setDate(String date) {
        this.date = date;
    }

    public int getTemp() {
        return temp;
    }
    @XmlAttribute
    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }
    @XmlAttribute
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "code=" + code +
                ", date=" + date +
                ", temp=" + temp +
                ", text='" + text + '\'' +
                '}';
    }
}
