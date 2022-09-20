package servlets.request;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@MultipartConfig
public class GetDataRequest implements RequestHandler{
    @Getter
    private final String dispatcher = "GetDataServlet";

    @Override
    public boolean isApplicable(HttpServletRequest req) {
        String param = req.getParameter("getData");
        System.out.println("checking getData - " + !(param==null));
        return !(param==null);
    }
}
