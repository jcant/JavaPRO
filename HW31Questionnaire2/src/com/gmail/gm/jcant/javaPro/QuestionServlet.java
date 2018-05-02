package com.gmail.gm.jcant.javaPro;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "Servlet", urlPatterns = "/")
public class QuestionServlet extends HttpServlet {

    private Map<String, Question> questions = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        questions.put("Question1", new Question("Do you like Java?"));
        questions.put("Question2", new Question("Do you like JavaScript?"));
        questions.put("Question3", new Question("Do you like .NET?"));
        questions.put("Question4", new Question("Do you like PHP?"));
        questions.put("Question5", new Question("Do you like C++?"));

    }

    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String sAge = req.getParameter("age");

        if (checkData(name, surname, sAge)!=true){
            resp.sendError(500);
            return;
        }

        Set<String> ids = questions.keySet();
        for (String id : ids) {
            String answer = (String) req.getParameter(id);
            if (answer.equals("YES")) {
                questions.get(id).addCountYes();
            } else {
                questions.get(id).addCountNo();
            }
        }

        req.setAttribute("questions", questions);
        this.getServletContext().getRequestDispatcher("/count.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("questions", questions);
        this.getServletContext().getRequestDispatcher("/questions.jsp").forward(req, resp);
    }

    private boolean checkData(String name, String surname, String sAge){
        int age = 0;

        try {
            age = Integer.parseInt(sAge);
        } catch (NumberFormatException e) {
            return false;
        }

        if ((name.equals(""))||(surname.equals(""))){
            return false;
        }

        return true;
    }
}
