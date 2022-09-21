package servlets;

import info.AppInfo;
import jakarta.servlet.http.HttpServlet;

public class AppServlet extends HttpServlet {
    protected AppInfo appInfo() {
        return (AppInfo) getServletContext().getAttribute(AppInfo.NAME);
    }
}
