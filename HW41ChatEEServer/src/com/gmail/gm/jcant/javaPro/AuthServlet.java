package com.gmail.gm.jcant.javaPro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "Auth", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {
    private UserList userList;

    @Override
    public void init() throws ServletException {
        super.init();
        userList = UserList.getInstance();
        
        Thread thKick = new Thread(new UserKickThread());
        thKick.setDaemon(true);
        thKick.start();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //System.out.println("IN GET");
        String login = req.getParameter("login");
        String passw = req.getParameter("passw");
        String exit = req.getParameter("exit");

        if ((exit!=null)&&(exit.equals("true"))) {
            userList.logoutUser(login);
        } else {

            String key = userList.authUser(login, passw);

            //System.out.println("key=" + key);

            if (key != null) {
                OutputStream os = resp.getOutputStream();
                byte[] buf = key.getBytes(StandardCharsets.UTF_8);
                os.write(buf);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
