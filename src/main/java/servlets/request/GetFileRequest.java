package servlets.request;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.util.List;

public class GetFileRequest implements RequestHandler {
//    private final List<String> resourceFormats = List.of("png", "gif", "jpg", "jpeg", "ico", "css", "js", "html");
    @Getter
    private final String dispatcher = "GetFileServlet";

    public boolean isApplicable(HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.contains("static/");
//        String[] split = uri.split("\\.");
//        String ender = split[split.length-1];
//        return resourceFormats.contains(ender);
    }
}
