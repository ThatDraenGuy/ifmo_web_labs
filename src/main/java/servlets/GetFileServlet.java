package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.request.RequestHandler;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "GetFileServlet")
public class GetFileServlet extends HttpServlet{

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


}
