package servlets;

import info.app.AppInfo;
import info.AttemptInfo;
import jakarta.servlet.http.HttpServlet;
import storage.HistoryManager;

public class AppServlet extends HttpServlet {
    protected AppInfo appInfo() {
        return (AppInfo) getServletContext().getAttribute(AppInfo.NAME);
    }
    protected HistoryManager<AttemptInfo> historyManager() {
        @SuppressWarnings({"unchecked"})
        HistoryManager<AttemptInfo> historyManager = (HistoryManager<AttemptInfo>) getServletContext().getAttribute(HistoryManager.NAME);
        return historyManager;
    }

}
