package com.gmail.gm.jcant.javaPro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gmail.gm.jcant.JDate;

@XmlRootElement(name = "train")
public class Train {
	private int id;
	private String from;
	private String to;
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
		return JDate.getDate(dateTime);
	}

	@XmlElement(name = "departure")
	public String getTime() {
		return JDate.getTime(dateTime);
	}

	public void setDate(String date) {
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
		Calendar our = Calendar.getInstance();
		Calendar setter = Calendar.getInstance();
		our.setTime(dateTime);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		try {
			setter.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		our.set(Calendar.HOUR, setter.get(Calendar.HOUR));
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

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", from=" + from + ", to=" + to + ", dateTime=" + dateTime + "]";
	}
}
