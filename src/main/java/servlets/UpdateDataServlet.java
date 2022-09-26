package servlets;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ValueException;
import info.SharedInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

@WebServlet(name = UpdateDataServlet.NAME)
@MultipartConfig
public class UpdateDataServlet extends AppServlet{
    public static final String NAME = "UpdateDataServlet";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println(req.getContentType());
            Map<String, String[]> params = req.getParameterMap();
            for (String key : params.keySet()) {
                System.out.println(key);
                for (String param : params.get(key)) {
                    System.out.println(param);
                }
                System.out.println();
            }
            appInfo().quadrantsInfo().update(params);

            resp.setContentType("text/plain");
            resp.getWriter().println("Data sufficiently updated");
        } catch (ValueException e) {
            resp.sendError(400, "Insufficient data sent to be updated! ");
        }
    }

    public static class Data implements ServletData {

        @Override
        public boolean isApplicable(HttpServletRequest req) {
            String param = req.getParameter("updateData");
            return !(param==null);
        }

        @Override
        public String getDispatcher() {
            return NAME;
        }
    }
}
