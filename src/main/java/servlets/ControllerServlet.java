package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name =ControllerServlet.NAME, urlPatterns = {"/",""})
@MultipartConfig
public class ControllerServlet extends HttpServlet {
    public final static String NAME = "controller";
    private final List<ServletData> servletData = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        servletData.add(new JspAccessServlet.Data());
        servletData.add(new GetFileServlet.Data());
        servletData.add(new GetDataServlet.Data());
        servletData.add(new AreaCheckServlet.Data());
        servletData.add(new ClearHistoryServlet.Data());
        servletData.add(new UpdateDataServlet.Data());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }
    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }
    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("startTime", Instant.now());

        for (ServletData servletData : this.servletData) {
            if (servletData.isApplicable(req)) {
                forward(req,resp, servletData.getDispatcher());
                return;
            }
        }
        //default
        forward(req,resp, NotFoundServlet.NAME);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, String dispatcherName) throws ServletException, IOException {
        log("forwarding to "+dispatcherName);
        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher(dispatcherName);
        dispatcher.forward(req,resp);
    }
}
