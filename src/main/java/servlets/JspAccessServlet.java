package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.AttemptInfo;
import domain.ReactionsInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.HistoryManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@WebServlet(name = JspAccessServlet.NAME)
public class JspAccessServlet extends AppServlet{
    public static final String NAME = "JspAccessServlet";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals(req.getServletContext().getContextPath()+"/")) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            setupResponseAttributes(req);
            getServletContext().getRequestDispatcher("/response.jsp").forward(req, resp);
        }
    }
    private void setupResponseAttributes(HttpServletRequest req) {
        HistoryManager<AttemptInfo> historyManager = historyManager(req);

        AttemptInfo attemptInfo;
        try {
            if (req.getSession().getAttribute("isInfoNew")!=null) {
                attemptInfo = (AttemptInfo) req.getSession().getAttribute("attemptInfo");
                historyManager.put(attemptInfo);
                req.getSession().removeAttribute("isInfoNew");
            } else {
                attemptInfo = historyManager.get().get(historyManager.get().size()-1);
            }
        } catch (Exception e) {
            attemptInfo = AttemptInfo.empty();
        }
        req.setAttribute("scream", attemptInfo.res() ? "HIT!" : "MISS!");

        ReactionsInfo reactionsInfo;
        try {
            InputStream inputStream = getServletContext().getResourceAsStream("/static/reactions.json");
            Reader reader = new InputStreamReader(inputStream);
            ObjectMapper mapper = new ObjectMapper();
            reactionsInfo = mapper.readValue(reader, ReactionsInfo.class);
        } catch (Exception e) {
            reactionsInfo = ReactionsInfo.empty();
        }
        req.setAttribute("reaction", attemptInfo.res() ? reactionsInfo.randomHit() : reactionsInfo.randomMiss());
        req.setAttribute("historyTable", historyToTable(historyManager.get()));
    }
    private String historyToTable(List<AttemptInfo> history) {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (AttemptInfo attemptInfo : history) {
            attemptInfo.toHtmlRow(builder, i);
            i++;
        }
        return builder.toString();
    }

    public static class Data implements ServletData {

        @Override
        public boolean isApplicable(HttpServletRequest req) {
            return  req.getRequestURI().equals(req.getServletContext().getContextPath()+"/") || req.getRequestURI().contains("jsp");
        }

        @Override
        public String getDispatcher() {
            return NAME;
        }
    }
}
