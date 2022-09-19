package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name ="controller", urlPatterns = "/")
public class ControllerServlet extends HttpServlet {
    private final List<String> resourceFormats = List.of("png", "gif", "jpg", "jpeg", "ico", "css", "js", "html");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        req.getHeaderNames().asIterator().forEachRemaining(System.out::println);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("testPost");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doFileGet(req, resp);



        System.out.println(req);
        req.getHeaderNames().asIterator().forEachRemaining(System.out::println);
    }


    private void doFileGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String[] split = uri.split("\\.");
        String ender = split[split.length-1];
        if (resourceFormats.contains(ender)) {
            System.out.println(uri);
            String[] split2 = uri.split("/", 3);
            String trueUri = split2[split2.length-1];
            System.out.println(trueUri);
            resp.setContentType(getServletContext().getMimeType(trueUri));

            getServletContext().getResourceAsStream(trueUri).transferTo(resp.getOutputStream());
        }
    }
}
