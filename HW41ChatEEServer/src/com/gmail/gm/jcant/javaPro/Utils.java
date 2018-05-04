package com.gmail.gm.jcant.javaPro;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static void outputWrite(HttpServletResponse resp, String text) throws IOException {
        OutputStream os = resp.getOutputStream();
        byte[] buf = text.getBytes(StandardCharsets.UTF_8);
        os.write(buf);
    }
}
