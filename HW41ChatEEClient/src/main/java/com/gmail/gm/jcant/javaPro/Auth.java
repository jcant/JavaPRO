package com.gmail.gm.jcant.javaPro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Auth {
    private String key;
    private String login;
    private String password;
    private Gson gson;

    public Auth() {
        super();
        gson = new GsonBuilder().setDateFormat("MMMMM d, yyyy h:m:s a").create();
    }

    public void doExit(){
        try {
            String resp = getData(Utils.getURL() + "/auth?login="+login+"&exit=true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doAuth(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your login: ");
        login = scanner.nextLine();
        System.out.println("Enter your password: ");
        password = scanner.nextLine();

        try {
            key = getData(Utils.getURL() + "/auth?login=" + login + "&passw=" + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("KEY returned="+key);
    }
    
    public void getUserStatus(String text) {
    	String url = Utils.getURL() + "/userstatus";
    	if (text.startsWith("!INFO:")) {
           url += "?login="+text.substring(6);
    	}
    	
    	try {
			String[] result = gson.fromJson(getData(url), String[].class);
			for (String item : result) {
				System.out.println(item);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    private String getData(String sUrl) throws IOException {
        String data = "";
        URL url = new URL(sUrl);
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();

        if (connect.getContentLengthLong() != 0) {

            try (InputStream is = connect.getInputStream(); InputStreamReader isr = new InputStreamReader(is,"utf-8")) {
                StringBuilder sb = new StringBuilder();

                BufferedReader br = new BufferedReader(isr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();
            }
        }

        return data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
