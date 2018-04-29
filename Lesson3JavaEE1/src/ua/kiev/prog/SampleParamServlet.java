package ua.kiev.prog;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SampleParamServlet extends HttpServlet {
	
	static final String TEMPLATE = "<html>" +
			"<head><title>Prog.kiev.ua</title></head>" +
			"<body><h1>%s</h1></body></html>";	
	
	// GET request: http://localhost:8888/testparam?age=23&position=junior
	// GET request: http://localhost:8888/testparam?age=20&position=senior
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String ageS = req.getParameter("age");
		String position = req.getParameter("position");
		String msg;
		// convert params
		int age = Integer.parseInt(ageS);
		
		if ("senior".equals(position) && (age < 22))
			msg = "Super Java developer";
		else if ("junior".equals(position) && (age > 15))
			msg = "It's great to be junior at any age";
		else {
			msg = "Wrong parameters";
			resp.setStatus(400); // bad request
		}
		
		resp.setContentType("text/html"); // Content-Type: text/html
        PrintWriter pw = resp.getWriter();
        pw.println(String.format(TEMPLATE, msg));
	}
}
