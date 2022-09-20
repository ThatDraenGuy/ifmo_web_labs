package servlets;

import constraints.Constraint;
import constraints.NoConstraint;
import coordinates.Quadrant;
import exceptions.ValueException;
import info.AppInfo;
import info.AttemptInfo;
import info.SharedInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;


@WebServlet(name = "AreaCheckServlet")
@MultipartConfig
public class AreaCheckServlet extends HttpServlet {
    private SharedInfo info;


    @Override
    public void init() throws ServletException {
        info = AppInfo.getInstance().getInfo();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AttemptInfo attemptInfo = null;
        try {
            double x = getValue(req,"x");
            double y = getValue(req,"y");
            double r = getValue(req,"r");
            for (Quadrant quadrant : info.getQuadrants()) {
                if (quadrant.checkHit(x,y,r)) {
                    attemptInfo = AttemptInfo.fromHit(req,x,y,r,true,"That's a hit!");
                    break;
                }
            }
            if (attemptInfo==null) attemptInfo = AttemptInfo.fromHit(req,x,y,r,false,"LOL, that's a miss!");
        } catch (ValueException e) {
            attemptInfo = AttemptInfo.fail(req, e.getMessage());
        }
        req.setAttribute("attemptInfo", attemptInfo);
        getServletContext().getRequestDispatcher("/response.jsp").forward(req,resp);
    }

    private double getValue(HttpServletRequest req, String name) throws ValueException {
        String param = req.getParameter(name);
        if (param==null) throw new ValueException("Value "+name+" wasn't inputted");
        try {
            double parsed = Double.parseDouble(param);
            if (AppInfo.getInstance().isCheckingConstraints()) {
                Constraint constraint = info.getConstraints().getOrDefault(param, new NoConstraint());
                if (!constraint.checkValue(parsed)) throw new ValueException("Value "+name+" didn't pass the constraint check");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new ValueException("Value "+name+" isn't a number, but a \""+param+"\"");
        }
    }

}
