package servlets.request;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
@MultipartConfig
public class AreaCheckRequest implements RequestHandler{
    @Getter
    private final String dispatcher = "AreaCheckServlet";

    @Override
    public boolean isApplicable(HttpServletRequest req) {
        String param = req.getParameter("shoot");
        return !(param==null);
    }
}
