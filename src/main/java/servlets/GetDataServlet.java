package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "GetDataServlet")
public class GetDataServlet extends HttpServlet {
    private final String temp = """
                {
                  "quadrants":[
                    {
                      "type":"circle",
                      "xMul":1,
                      "yMul":1
                    },
                    {
                      "type":"square",
                      "xMul":-1,
                      "yMul":1
                    },
                    {
                      "type":"triangle",
                      "xMul":-1,
                      "yMul":-1
                    },
                    {
                      "type":"empty",
                      "xMul":1,
                      "yMul":-1
                    }],
                  "constraints":{
                    "x":{
                      "type":"options",
                      "options":[-4,-3,-2,-1,0,1,2,3,4]
                    },
                    "y":{
                      "type":"range",
                      "min":-3,
                      "max":3
                    },
                    "r":{
                      "type":"options",
                      "options":[1,1.5,2,2.5,3]
                    }
                  }
                }
            """;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().print(temp);
    }
}
