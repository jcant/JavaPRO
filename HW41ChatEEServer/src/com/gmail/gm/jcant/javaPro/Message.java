package com.gmail.gm.jcant.javaPro;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;
	private int number;

    public Message(){
        super();
    }
    
    public Message(String from, String text) {
        super();
        this.from = from;
        parseTo(text);
    }
    
    private void parseTo(String text) {
        if (text.startsWith("!TO:")) {
            this.to = text.substring(4, text.indexOf(' ', 4));
            this.text = text.substring(text.indexOf(' ', 4) + 1);
        } else {
            this.text = text;
        }
    }

	public String toJSON() {
		Gson gson = new GsonBuilder().setDateFormat("MMMMM d, yyyy h:m:s a").create();
		return gson.toJson(this);
	}

	public static Message fromJSON(String s) {
		Gson gson = new GsonBuilder().setDateFormat("MMMMM d, yyyy h:m:s a").create();
		return gson.fromJson(s, Message.class);
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return new StringBuilder().append("[").append(sdf.format(date)).append(", From: ").append(from).append(", To: ")
				.append(to).append("] ").append(text).toString();
	}
	
    public void print(String user) {
        StringBuilder mess = new StringBuilder();
        if (to != null) {
            if (to.equals(user)) {
                mess.append("PRIVATE (from ").append(from).append(")").append(System.lineSeparator()).append(this);
            } else if (from.equals(user)) {
                mess.append("PRIVATE (to ").append(to).append(")").append(System.lineSeparator()).append(this);
            }
        } else {
            mess.append(this);
        }

        System.out.println(mess.toString());
    }

	public int send(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		try (OutputStream os = conn.getOutputStream()) {
			String json = toJSON();
			os.write(json.getBytes(StandardCharsets.UTF_8));
			return conn.getResponseCode();
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
