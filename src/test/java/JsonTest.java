import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constraints.Options;
import constraints.Range;
import coordinates.CircleQuadrant;
import coordinates.EmptyQuadrant;
import coordinates.SquareQuadrant;
import coordinates.TriangleQuadrant;
import info.AttemptInfo;
import info.SharedInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class JsonTest {
    @Test
    public void doTest() throws JsonProcessingException {
        SharedInfo sharedInfo = new SharedInfo();
        sharedInfo.quadrants().add(new CircleQuadrant(0.5,0.5));
        sharedInfo.quadrants().add(new SquareQuadrant(-0.5,0.5));
        sharedInfo.quadrants().add(new TriangleQuadrant(-0.5,-0.5));
        sharedInfo.quadrants().add(new EmptyQuadrant(0.5,-0.5));

        sharedInfo.constraints().put("x",new Options(new double[]{-4, -3, -2, -1, 0, 1, 2, 3, 4}));
        sharedInfo.constraints().put("y",new Range(-3,3));
        sharedInfo.constraints().put("r",new Options(new double[]{1, 1.5, 2, 2.5, 3}));

        sharedInfo.history().add(new AttemptInfo("1","1","1",true,"hit", Duration.ZERO, LocalDateTime.now()));

        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

//        mapper.registerSubtypes(CircleQuadrant.class, SquareQuadrant.class, TriangleQuadrant.class, EmptyQuadrant.class);

        String res = mapper.writeValueAsString(sharedInfo);
        System.out.println(res);
        SharedInfo parsed = mapper.readValue(res, SharedInfo.class);
        System.out.println(parsed);
        String resNew = mapper.writeValueAsString(parsed);
        System.out.println(resNew);
        Assertions.assertEquals(res, resNew);
    }

    @Test
    public void testOldJson() throws JsonProcessingException {
        String json = """
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
        ObjectMapper mapper = new ObjectMapper();
        SharedInfo sharedInfo = mapper.readValue(json, SharedInfo.class);
        System.out.println(sharedInfo);
        String newJson = mapper.writeValueAsString(sharedInfo);
    }
}

