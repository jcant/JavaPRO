package com.gmail.gm.jcant.javaPro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetList", urlPatterns = "/get")
public class MessageListServlet extends HttpServlet {

	private MessageList msgList;
	private UserList userList;

	@Override
	public void init() throws ServletException {
		super.init();
		msgList = MessageList.getInstance();
		userList = UserList.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String fromStr = req.getParameter("from");
		String key = req.getParameter("key");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			ex.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		User user = userList.getAuthUser(key);
		//System.out.println("get request: from="+from+" key="+key+" user: "+user);
		if (user != null) {
			String json = msgList.toJSON(from, user);
			if (json != null) {
				Utils.outputWrite(resp, json);
			}
		}
	}
}
