package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = JspAccessServlet.NAME)
public class JspAccessServlet extends AppServlet{
    public static final String NAME = "JspAccessServlet";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals(req.getServletContext().getContextPath()+"/")) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/response.jsp").forward(req, resp);
        }
    }

    public static class Data implements ServletData {

        @Override
        public boolean isApplicable(HttpServletRequest req) {
            return  req.getRequestURI().equals(req.getServletContext().getContextPath()+"/") || req.getRequestURI().contains("jsp");
        }

        @Override
        public String getDispatcher() {
            return NAME;
        }
    }
}
