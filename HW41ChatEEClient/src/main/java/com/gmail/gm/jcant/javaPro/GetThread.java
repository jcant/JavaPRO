package com.gmail.gm.jcant.javaPro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private String user;

    public GetThread(String user) {
        this.user = user;
        gson = new GsonBuilder().setDateFormat("MMMMM d, yyyy h:m:s a").create();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + n + "&user=" + user);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();


                try (InputStream is = http.getInputStream()) {
                    byte[] buf = requestBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);
                    //System.out.println(strBuf);
                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    //Message[] list = gson.fromJson(strBuf, Message[].class);
                    //System.out.println(list);
                    if (list != null) {
                        for (Message m : list.getList()) {
                            //System.out.println("MESSAGE: "+m);
                            printMessage(m);
                            //System.out.println(m);
                            //n++;
                        }
                        n = list.getSize();
                        //System.out.println("SIZE="+n);
                    }
                }

                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printMessage(Message m) {
        String mess = "";
        if (m == null) {
            return;
        }
        if (m.getTo() != null) {
            if (m.getTo().equals(user)) {
                mess += "PRIVATE (from " + m.getFrom() + ")" + System.lineSeparator() + m;
            } else if (m.getFrom().equals(user)) {
                mess += "PRIVATE (to " + m.getTo() + ")" + System.lineSeparator() + m;
            }
        } else {
            mess += m;
        }

        System.out.println(mess);
    }

    private byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
