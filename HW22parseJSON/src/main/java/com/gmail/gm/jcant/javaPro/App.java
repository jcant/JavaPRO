package com.gmail.gm.jcant.javaPro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {
    public static void main(String[] args) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        Person pers2 = null;
        try {
            pers2 = gson.fromJson(new FileReader(new File("./data.json")), Person.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(pers2);
    }
}
