package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = GetFileServlet.NAME)
public class GetFileServlet extends HttpServlet{
    public static final String NAME = "GetFileServlet";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        String trueUri = uri.replace(getServletContext().getContextPath(), "");
        resp.setContentType(getServletContext().getMimeType(trueUri));
        getServletContext().getResourceAsStream(trueUri).transferTo(resp.getOutputStream());
    }

    public static class Data implements ServletData {
        public boolean isApplicable(HttpServletRequest req) {
            String uri = req.getRequestURI();
            return uri.contains("static/");
        }

        @Override
        public String getDispatcher() {
            return NAME;
        }
    }
}
