package servlets;

import info.app.AppInfo;
import info.AttemptInfo;
import info.app.AppInfoProvider;
import info.app.ConfigAppInfoProvider;
import info.app.SampleAppInfoProvider;
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
    private SessionHistoryManager<AttemptInfo> historyManager;
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        getServletContext().log("request: "+req.getMethod()+" "+req.getRequestURI());
        if (isSet()) {
            historyManager.updateSession(req.getSession());
        } else {
            createAppInfo(req);
        }
        if (req.getDispatcherType().name().equals("FORWARDED")) {
            chain.doFilter(req, res);
        } else {
            getServletContext().getNamedDispatcher(ControllerServlet.NAME).forward(req,res);
        }
    }

    private void createAppInfo(HttpServletRequest req) {
        historyManager = new SessionHistoryManager<>();
        getServletContext().setAttribute(HistoryManager.NAME, historyManager);
        AppInfo appInfo = appInfoProvider.get();
        getServletContext().setAttribute(AppInfo.NAME, appInfo);
    }
    private boolean isSet() {
        return getServletContext().getAttribute(AppInfo.NAME) !=null;
    }
}
