package com.gmail.gm.jcant.javaPro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(name = "UserStatus", urlPatterns = "/userstatus")
public class UserStatusServlet extends HttpServlet {

	private UserList userList;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		super.init();
		userList = UserList.getInstance();
		gson = new GsonBuilder().setDateFormat("MMMMM d, yyyy h:m:s a").create();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String login = req.getParameter("login");

		String list = gson.toJson(userList.getUsersStatusList(login));

		if (list != null) {
			Utils.outputWrite(resp,list);
		} else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}