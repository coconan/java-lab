package me.coconan.cucumber.atm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WithdrawalServlet extends HttpServlet {
    private final CashSlot cashSlot;
    private final Account account;

    public WithdrawalServlet(CashSlot cashSlot, Account account) {
        this.cashSlot = cashSlot;
        this.account = account;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Teller teller = new AutomatedTeller(cashSlot);
        int amount = Integer.parseInt(req.getParameter("amount"));
        teller.withdrawFrom(account, amount);

        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(
                "<html><head><title>Nice Bank ATM</title></head>" +
                        "<body>Please take your $" + amount + "</body>" +
                        "</html>");
    }
}
