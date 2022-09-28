package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = NotFoundServlet.NAME)
public class NotFoundServlet extends AppServlet{
    public static final String NAME = "NotFoundServlet";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("Not found!");
        resp.getWriter().println("(beautiful page not created because laziness)");
    }
}
