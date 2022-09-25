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
        return (HistoryManager<AttemptInfo>) getServletContext().getAttribute(HistoryManager.NAME);
    }

}
