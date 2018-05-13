package com.gmail.gm.jcant.javaPro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
	private final Gson gson;
	private int n;
	//private String user;
	private UserWorker userWorker;

	public GetThread(UserWorker userWorker) {
		this.userWorker = userWorker;
		gson = new GsonBuilder().setDateFormat("MMMMM d, yyyy h:m:s a").create();
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				URL url = new URL(Utils.getURL() + "/get?from=" + n + "&key=" + userWorker.getKey());
				HttpURLConnection http = (HttpURLConnection) url.openConnection();

				try (InputStream is = http.getInputStream()) {
					byte[] buf = requestBodyToArray(is);
					String strBuf = new String(buf, StandardCharsets.UTF_8);
					JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
					if (list != null) {
						for (Message m : list.getList()) {
							m.print(userWorker.getLogin());
							n = m.getNumber();
						}
					}
				}

				Thread.sleep(500);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private byte[] requestBodyToArray(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[10240];
		int r;

		do {
			r = is.read(buf);
			if (r > 0)
				bos.write(buf, 0, r);
		} while (r != -1);

		return bos.toByteArray();
	}
}
