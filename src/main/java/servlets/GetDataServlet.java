package servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.AppInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = GetDataServlet.NAME)
public class GetDataServlet extends HttpServlet {
    public static final String NAME = "GetDataServlet";
    private final ObjectMapper mapper = new ObjectMapper();
    private String jsonInfo;

    @Override
    public void init() throws ServletException {
        try {
            jsonInfo = mapper.writeValueAsString(AppInfo.getInstance().getInfo());
        } catch (JsonProcessingException e) {
            jsonInfo = "{}";
            //TODO
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().print(jsonInfo);
    }

    public static class Data implements ServletData {
        @Override
        public boolean isApplicable(HttpServletRequest req) {
            String param = req.getParameter("getData");
            return !(param==null);
        }

        @Override
        public String getDispatcher() {
            return NAME;
        }
    }
}
