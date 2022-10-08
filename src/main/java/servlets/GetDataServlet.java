package servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.SharedInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = GetDataServlet.NAME)
public class GetDataServlet extends AppServlet {
    public static final String NAME = "GetDataServlet";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonInfo;
        try {
            SharedInfo sharedInfo = new SharedInfo(appInfo(req), historyManager(req));
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
