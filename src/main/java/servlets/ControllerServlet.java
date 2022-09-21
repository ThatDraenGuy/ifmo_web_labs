package servlets;

import info.AppInfo;
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

@WebServlet(name ="controller", urlPatterns = "/")
@MultipartConfig
public class ControllerServlet extends HttpServlet {
    private final List<ServletData> servletData = new ArrayList<>();
    private final String defaultDispatcher = "webLab-2.0-SNAPSHOT/index.jsp";

    @Override
    public void init() throws ServletException {
        servletData.add(new GetFileServlet.Data());
        servletData.add(new GetDataServlet.Data());
        servletData.add(new AreaCheckServlet.Data());
        servletData.add(new ClearHistoryServlet.Data());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("handling...");
        AppInfo.getInstance().update(req);

        req.setAttribute("startTime", Instant.now());

        for (ServletData servletData : this.servletData) {
            if (servletData.isApplicable(req)) {
                forward(req,resp, servletData.getDispatcher());
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
