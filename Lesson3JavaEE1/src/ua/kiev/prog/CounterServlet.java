package ua.kiev.prog;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CounterServlet extends HttpServlet {
	
	private int x;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// int x = 0;
		resp.getWriter().println("<html><h1>" + (x++) + "</h1></html>");
	}
}
