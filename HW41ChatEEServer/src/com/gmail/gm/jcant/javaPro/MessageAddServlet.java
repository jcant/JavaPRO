package com.gmail.gm.jcant.javaPro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Add", urlPatterns = "/add")
public class MessageAddServlet extends HttpServlet {

	private MessageList msgList;
	private UserList userList;

	@Override
	public void init() throws ServletException {
		super.init();
		msgList = MessageList.getInstance();
		userList = UserList.getInstance();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte[] buf = requestBodyToArray(req);
		String bufStr = new String(buf, StandardCharsets.UTF_8);
		Message msg = Message.fromJSON(bufStr);

		if (msg != null) {
			User user = userList.getAuthUser(msg.getFrom());
			if (user != null) {
				msg.setFrom(user.getLogin());
				msg.setRoom(user.getRoom());
				msgList.add(msg);
			} else {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
	}

	private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
		InputStream is = req.getInputStream();
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
