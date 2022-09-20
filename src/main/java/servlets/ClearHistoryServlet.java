package servlets;

import info.AttemptInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
@WebServlet(name = ClearHistoryServlet.NAME)
public class ClearHistoryServlet extends HttpServlet {
    public static final String NAME = "ClearHistoryServlet";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("history", new ArrayList<AttemptInfo>());
        resp.setContentType("application/text");
        resp.getWriter().println("success");
    }

    public static class Data implements ServletData {

        @Override
        public boolean isApplicable(HttpServletRequest req) {
            String param = req.getParameter("clearHistory");
            return !(param==null);
        }

        @Override
        public String getDispatcher() {
            return NAME;
        }
    }
}
