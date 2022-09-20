import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constraints.Options;
import constraints.Range;
import coordinates.CircleQuadrant;
import coordinates.EmptyQuadrant;
import coordinates.SquareQuadrant;
import coordinates.TriangleQuadrant;
import info.Info;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonTest {
    @Test
    public void doTest() throws JsonProcessingException {
        Info info = new Info();
        info.getQuadrants().add(new CircleQuadrant(0.5,0.5));
        info.getQuadrants().add(new SquareQuadrant(-0.5,0.5));
        info.getQuadrants().add(new TriangleQuadrant(-0.5,-0.5));
        info.getQuadrants().add(new EmptyQuadrant(0.5,-0.5));

        info.getConstraints().put("x",new Options(new double[]{-4, -3, -2, -1, 0, 1, 2, 3, 4}));
        info.getConstraints().put("y",new Range(-3,3));
        info.getConstraints().put("r",new Options(new double[]{1, 1.5, 2, 2.5, 3}));

        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

//        mapper.registerSubtypes(CircleQuadrant.class, SquareQuadrant.class, TriangleQuadrant.class, EmptyQuadrant.class);

        String res = mapper.writeValueAsString(info);
        System.out.println(res);
        Info parsed = mapper.readValue(res, Info.class);
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
        Info info = mapper.readValue(json, Info.class);
        System.out.println(info);
        String newJson = mapper.writeValueAsString(info);
    }
}

