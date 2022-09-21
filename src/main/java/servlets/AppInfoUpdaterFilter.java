package servlets;

import info.AppInfo;
import info.AttemptInfo;
import info.InfoProvider;
import info.SampleInfoProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.HttpSessionManager;

import java.io.IOException;
@WebFilter(urlPatterns = "/", filterName = "AppInfoUpdateFilter", servletNames = ControllerServlet.NAME)
public class AppInfoUpdaterFilter extends HttpFilter {
    private final InfoProvider infoProvider = new SampleInfoProvider();
    private HttpSessionManager<AttemptInfo> historyManager;
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter!");
        if (isSet()) {
            historyManager.updateSession(req.getSession());
        } else {
            createAppInfo(req);
        }
        chain.doFilter(req,res);
    }

    private void createAppInfo(HttpServletRequest req) {
        historyManager = new HttpSessionManager<>(req.getSession());
        AppInfo appInfo = new AppInfo(infoProvider.get(), false, historyManager);
        getServletContext().setAttribute(AppInfo.NAME, appInfo);
    }
    private boolean isSet() {
        return getServletContext().getAttribute(AppInfo.NAME) !=null;
    }
}
