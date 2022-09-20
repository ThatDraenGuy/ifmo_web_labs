package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.request.AreaCheckRequest;
import servlets.request.GetDataRequest;
import servlets.request.GetFileRequest;
import servlets.request.RequestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name ="controller", urlPatterns = "/")
@MultipartConfig
public class ControllerServlet extends HttpServlet {
    private final List<RequestHandler> requestHandlers = new ArrayList<>();
    private final String defaultDispatcher = "webLab-2.0-SNAPSHOT/index.jsp";

    @Override
    public void init() throws ServletException {
        requestHandlers.add(new GetFileRequest());
        requestHandlers.add(new GetDataRequest());
        requestHandlers.add(new AreaCheckRequest());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
//        System.out.println(req);
//        System.out.println(req.getParameter("getData"));
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//        out.println("testPost");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
//        System.out.println(req);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("handling...");
        for (RequestHandler requestHandler : requestHandlers) {
            if (requestHandler.isApplicable(req)) {
                forward(req,resp, requestHandler.getDispatcher());
                return;
            }
        }
        System.out.println("default");
        forward(req,resp,defaultDispatcher);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String dispatcherName) throws ServletException, IOException {
        System.out.println(dispatcherName);
        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher(dispatcherName);
        dispatcher.forward(req,resp);
    }
}
