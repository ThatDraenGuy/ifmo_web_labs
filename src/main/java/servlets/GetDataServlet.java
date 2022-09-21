package servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.AttemptInfo;
import info.SharedInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = GetDataServlet.NAME)
public class GetDataServlet extends AppServlet {
    public static final String NAME = "GetDataServlet";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonInfo;
        try {
            SharedInfo sharedInfo = appInfo().sharedInfo();
            try {
                List<AttemptInfo> history = (List<AttemptInfo>) req.getSession().getAttribute("history");
                if (history==null) throw new NullPointerException();
                sharedInfo.setHistory(history);
            } catch (ClassCastException | NullPointerException ignored) {}
            jsonInfo = mapper.writeValueAsString(sharedInfo);
        } catch (JsonProcessingException e) {
            jsonInfo = "{}";
            //TODO
        }

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
