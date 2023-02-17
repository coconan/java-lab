package me.coconan.cucumber.atm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AtmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(
                "<html><head><title>ATM</title>" +
                        "<script src=\"js/jquery.1.9.1.min.js\"></script>" +
                        "<script src=\"js/notifications.js\"></script>" +
                        "</head><body>" +
                        "<form id=\"withdrawalForm\" " +
                        "action=\"/withdraw\" method=\"post\">" +
                        "<label for=\"amount\">Amount</label>" +
                        "<input type=\"text\" id=\"amount\" " +
                        "name=\"amount\" autocomplete=\"off\">" +
                        "<button type=\"submit\" id=\"withdraw\">Withdraw</button>" +
                        "<ol class=\"notifications\">" +
                        "</ol></form></body>" +
                        "</html>"
        );
    }
}
