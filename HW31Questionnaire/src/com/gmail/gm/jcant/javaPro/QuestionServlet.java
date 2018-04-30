package com.gmail.gm.jcant.javaPro;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet", urlPatterns = "/count")
public class QuestionServlet extends HttpServlet {
    static final int QUESTION_1_YES = 0;
    static final int QUESTION_1_NO = 1;
    static final int QUESTION_2_YES = 2;
    static final int QUESTION_2_NO = 3;

    static final String TEMPLATE = "<html>" +
            "<head><title>Answers</title></head>" +
            "<body><h1>%s</h1></body></html>";

    final int[] results = new int[4];

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String q1 = req.getParameter("question1");
        final String q2 = req.getParameter("question2");

        final int idx1 = "YES".equals(q1) ? QUESTION_1_YES : QUESTION_1_NO;
        final int idx2 = "YES".equals(q2) ? QUESTION_2_YES : QUESTION_2_NO;

        results[idx1]++;
        results[idx2]++;

        String res = "<p>Question1: yes = " + results[QUESTION_1_YES] +
                ", no = " + results[QUESTION_1_NO] + "</p>" +
                "<p>Question2: yes = " + results[QUESTION_2_YES] +
                ", no = " + results[QUESTION_2_NO] + "</p>";

        resp.getWriter().println(String.format(TEMPLATE, res));
    }
}
