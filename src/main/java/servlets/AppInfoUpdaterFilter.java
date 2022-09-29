package servlets;

import info.app.AppInfo;
import info.AttemptInfo;
import info.app.AppInfoProvider;
import info.app.ConfigAppInfoProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.HistoryManager;
import storage.SessionHistoryManager;

import java.io.IOException;
@WebFilter(urlPatterns = "/*", filterName = "AppInfoUpdateFilter")
public class AppInfoUpdaterFilter extends HttpFilter {
    private final AppInfoProvider appInfoProvider = new ConfigAppInfoProvider();
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        getServletContext().log("request: "+req.getMethod()+" "+req.getRequestURI());
        if (!isCreated(req)) {
            createAppInfo(req);
        }
        handleSession(req);
        getServletContext().getNamedDispatcher(ControllerServlet.NAME).forward(req,res);
    }

    private void createAppInfo(HttpServletRequest req) {
        AppInfo appInfo = appInfoProvider.get();
        getServletContext().setAttribute(AppInfo.NAME, appInfo);
    }
    private void handleSession(HttpServletRequest req) {
        if (req.getSession().getAttribute(AppInfo.NAME) == null) {
            req.getSession().setAttribute(AppInfo.NAME, getServletContext().getAttribute(AppInfo.NAME));
            SessionHistoryManager<AttemptInfo> historyManager = new SessionHistoryManager<>(req.getSession());
            req.getSession().setAttribute(HistoryManager.NAME, historyManager);
        }
    }
    private boolean isCreated(HttpServletRequest req) {
        return getServletContext().getAttribute(AppInfo.NAME) !=null;
    }
}
