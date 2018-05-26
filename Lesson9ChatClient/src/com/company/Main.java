package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class GetThread extends Thread {
	private Date from = new Date(0);

	@Override
	public void run() {
		try {
			while (!isInterrupted()) {
				URL url = new URL("http://localhost:8080/get?from=" + from.getTime());
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int r;

				InputStream is = http.getInputStream();
				try {
					do {
						byte[] buf = new byte[is.available()];
						r = is.read(buf);
						if (r > 0)
							bos.write(buf, 0, r);
					} while (r > 0);

					Gson gson = new GsonBuilder()
								.setDateFormat("yyyy-MM-dd HH:mm:ss")
								.create();

					Message[] list = gson.fromJson(new String(bos.toByteArray()), Message[].class);
					if (list != null) {
						for (Message m : list) {
							System.out.println(m);
							from = m.getDate();
						}
					}
				} finally {
					is.close();
				}

				Thread.sleep(1000);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));

		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter login: ");
			String login = scanner.nextLine();
	
			GetThread th = new GetThread();
			th.setDaemon(true);
			th.start();

			while (true) {
				String text = scanner.nextLine();
				if (text.isEmpty())
					break;

				Message m = new Message();
				m.setText(text);
				m.setFrom(login);

				try {
					int res = m.send("http://localhost:8080/add");
					if (res != 200) {
						System.out.println("HTTP error: " + res);
						return;
					}
				} catch (IOException ex) {
					System.out.println("Error: " + ex.getMessage());
					return;
				}
			}
		} finally {
			scanner.close();
		}
	}
}
