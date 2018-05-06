package ua.kiev.prog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class HandlerServlet extends HttpServlet {
    private Orders orders = new Orders();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brandStr = req.getParameter("brand");
        String priceStr = req.getParameter("price");
        List<Order> list;

        if (priceStr != null) {
            int price = Integer.parseInt(priceStr);
            list = orders.get(price);
            req.setAttribute("price", true);
        } else if (brandStr != null) {
            list = orders.get(brandStr);
            req.setAttribute("brand", brandStr);
        } else {
            list = orders.get(null);
        }

        req.setAttribute("ordersList", list);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}
