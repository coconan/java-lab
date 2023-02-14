package me.coconan.cucumber.atm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AtmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(
                "<html><head><title>ATM</title></head>" +
                        "<body><form action=\"/withdraw\" method=\"post\">" +
                        "<label for=\"amount\">Amount</label>" +
                        "<input type=\"text\" id=\"amount\" name=\"amount\">" +
                        "<button type=\"submit\" id=\"withdraw\">Withdraw</button>" +
                        "</form></body></html>"
        );
    }
}
