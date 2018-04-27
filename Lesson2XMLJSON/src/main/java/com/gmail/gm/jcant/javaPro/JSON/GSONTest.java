package com.gmail.gm.jcant.javaPro.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONTest {

	public static void test() throws Exception {

		String request = "https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20%3D%202487889&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

		String result = performRequest(request);

		Gson gson = new GsonBuilder().create();
		JSON json = (JSON) gson.fromJson(result, JSON.class);

		for (Rate rate : json.query.results.rate) {
			System.out.println(rate.id + "=" + rate.Rate);
		}

		System.out.println("JSON: \n\t" + gson.toJson(json));
	}

	private static String performRequest(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		StringBuilder sb = new StringBuilder();

		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
			char[] buf = new char[1000000];

			int r = 0;
			do {
				if ((r = br.read(buf)) > 0)
					sb.append(new String(buf, 0, r));
			} while (r > 0);
		} finally {
			http.disconnect();
		}

		return sb.toString();
	}

}