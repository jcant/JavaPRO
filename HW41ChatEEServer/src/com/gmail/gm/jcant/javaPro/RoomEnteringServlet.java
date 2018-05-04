package com.gmail.gm.jcant.javaPro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RoomEntering", urlPatterns = "/room")
public class RoomEnteringServlet extends HttpServlet {

    private UserList userList;

    @Override
    public void init() throws ServletException {
        super.init();
        userList = UserList.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String room = req.getParameter("room");
        String key = req.getParameter("key");
        String exit = req.getParameter("exit");

        User user = userList.getAuthUser(key);

        if (user != null) {
            if ((exit != null) && (exit.equals("true"))) {
                user.setRoom(null); //exit room
            } else {
                user.setRoom(room); //enter room
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
