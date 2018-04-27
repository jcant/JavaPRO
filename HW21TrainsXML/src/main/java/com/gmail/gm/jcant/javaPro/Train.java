package com.gmail.gm.jcant.javaPro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.gmail.gm.jcant.JDate;

@XmlRootElement(name = "train")
public class Train {
    private int id;
    private String from;
    private String to;
    @XmlTransient
    private Date dateTime;


    public Train() {
        super();
    }

    public Train(int id, String from, String to, Date dateTime) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "date")
    public String getDate() {
        if (dateTime == null) {
            return null;
        } else {
            JDate.setDefaultDateFormat("dd.MM.yyyy");
            return JDate.getDate(dateTime);
        }
    }

    @XmlElement(name = "departure")
    public String getTime() {
        if (dateTime == null) {
            return null;
        } else {
            JDate.setDefaultTimeFormat("HH:mm");
            return JDate.getTime(dateTime);
        }
    }

    public void setDate(String date) {
        if (dateTime == null) {
            dateTime = new Date();
        }
        Calendar our = Calendar.getInstance();
        Calendar setter = Calendar.getInstance();
        our.setTime(dateTime);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            setter.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        our.set(setter.get(Calendar.YEAR), setter.get(Calendar.MONTH), setter.get(Calendar.DAY_OF_MONTH));
        dateTime = our.getTime();

    }

    public void setTime(String time) {
        if (dateTime == null) {
            dateTime = new Date();
        }
        Calendar our = Calendar.getInstance();
        Calendar setter = Calendar.getInstance();
        our.setTime(dateTime);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            setter.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        our.set(Calendar.HOUR_OF_DAY, setter.get(Calendar.HOUR_OF_DAY));
        our.set(Calendar.MINUTE, setter.get(Calendar.MINUTE));
        our.set(Calendar.SECOND, 0);
        dateTime = our.getTime();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDateTime(){
        return dateTime;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
