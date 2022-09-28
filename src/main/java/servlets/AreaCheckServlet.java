package servlets;

import exceptions.ValueException;
import info.AttemptInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;


@WebServlet(name = AreaCheckServlet.NAME)
@MultipartConfig
public class AreaCheckServlet extends AppServlet {
    public final static String NAME = "AreaCheckServlet";


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AttemptInfo attemptInfo = null;
        try {
            double x = getValue(req,"x");
            double y = getValue(req,"y");
            double r = getValue(req,"r");
            attemptInfo = appInfo().quadrantsInfo().check(startTime(req), x,y,r);
        } catch (ValueException e) {
            attemptInfo = AttemptInfo.fail(req, e.getMessage());
        }
        req.setAttribute("attemptInfo", attemptInfo);
        getServletContext().getRequestDispatcher("/WEB-INF/response.jsp").forward(req,resp);
    }

    private double getValue(HttpServletRequest req, String name) throws ValueException {
        String param = req.getParameter(name);
        if (param==null) throw new ValueException("Value "+name+" wasn't inputted");
        try {
            double parsed = Double.parseDouble(param);
            appInfo().constraintsInfo().check(name, parsed);
            return parsed;
        } catch (NumberFormatException e) {
            throw new ValueException("Value "+name+" isn't a number, but a \""+param+"\"");
        }
    }
    private Instant startTime(HttpServletRequest req) {
        try {
            return (Instant) req.getAttribute("startTime");
        } catch (ClassCastException e) {
            return Instant.now();
        }
    }

    public static class Data implements ServletData {

        @Override
        public boolean isApplicable(HttpServletRequest req) {
            String param = req.getParameter("shoot");
            return !(param==null);
        }

        @Override
        public String getDispatcher() {
            return NAME;
        }
    }
}
