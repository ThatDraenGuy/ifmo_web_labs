package servlets;

import domain.app.AppInfo;
import domain.AttemptInfo;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import storage.HistoryManager;

public class AppServlet extends HttpServlet {
    protected AppInfo appInfo(HttpServletRequest req) {
        return (AppInfo) req.getSession().getAttribute(AppInfo.NAME);
    }
    protected HistoryManager<AttemptInfo> historyManager(HttpServletRequest req) {
        @SuppressWarnings({"unchecked"})
        HistoryManager<AttemptInfo> historyManager = (HistoryManager<AttemptInfo>) req.getSession().getAttribute(HistoryManager.NAME);
        return historyManager;
    }

}
