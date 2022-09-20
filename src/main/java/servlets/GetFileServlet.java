package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = GetFileServlet.NAME)
public class GetFileServlet extends HttpServlet{
    public static final String NAME = "GetFileServlet";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        System.out.println(uri);
        String[] split2 = uri.split("/", 3);
        String trueUri = split2[split2.length-1];
        System.out.println(trueUri);
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
