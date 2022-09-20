package servlets;

import jakarta.servlet.http.HttpServletRequest;

public interface ServletData {
    boolean isApplicable(HttpServletRequest req);
    String getDispatcher();
}
