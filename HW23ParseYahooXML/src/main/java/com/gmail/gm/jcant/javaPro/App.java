package com.gmail.gm.jcant.javaPro;


import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20%3D%202487889&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        System.out.println("***");

        Query query = YahooLoader.getRequestResult(url);
        System.out.println(query);
    }
}
