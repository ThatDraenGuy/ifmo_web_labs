package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = ClearHistoryServlet.NAME)
public class ClearHistoryServlet extends AppServlet {
    public static final String NAME = "ClearHistoryServlet";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        historyManager(req).clear();
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
