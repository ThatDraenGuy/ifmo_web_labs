package servlets.request;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestHandler {
    boolean isApplicable(HttpServletRequest req);
    String getDispatcher();
}
