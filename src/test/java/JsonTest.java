import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constraints.Constraint;
import constraints.Options;
import constraints.Range;
import coordinates.*;
import info.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.EmptyHistoryManager;
import storage.SessionHistoryManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {
    @Test
    public void doTest() throws JsonProcessingException {
        List<Quadrant> quadrantList = new ArrayList<>();
        quadrantList.add(new CircleQuadrant(0.5,0.5));
        quadrantList.add(new SquareQuadrant(-0.5,0.5));
        quadrantList.add(new TriangleQuadrant(-0.5,-0.5));
        quadrantList.add(new EmptyQuadrant(0.5,-0.5));
        QuadrantsInfo quadrantsInfo = new QuadrantsInfo(quadrantList);

        Map<String, Constraint> constraintMap = new HashMap<>();
        constraintMap.put("x",new Options(new double[]{-4, -3, -2, -1, 0, 1, 2, 3, 4}));
        constraintMap.put("y",new Range(-3,3));
        constraintMap.put("r",new Options(new double[]{1, 1.5, 2, 2.5, 3}));
        ConstraintsInfo constraintsInfo = new ConstraintsInfo(constraintMap, false);

        ObjectMapper mapper = new ObjectMapper();
        String stuff = mapper.writeValueAsString(quadrantsInfo);
        System.out.println(stuff);
        String stuff2 = mapper.writeValueAsString(constraintsInfo);
        System.out.println(stuff2);

        AppInfo appInfo = new AppInfo(quadrantsInfo, constraintsInfo);
        String stuff3 = mapper.writeValueAsString(appInfo);
        System.out.println(stuff3);

        SharedInfo sharedInfo = new SharedInfo();
        sharedInfo.quadrants().add(new CircleQuadrant(0.5,0.5));
        sharedInfo.quadrants().add(new SquareQuadrant(-0.5,0.5));
        sharedInfo.quadrants().add(new TriangleQuadrant(-0.5,-0.5));
        sharedInfo.quadrants().add(new EmptyQuadrant(0.5,-0.5));

        sharedInfo.constraints().put("x",new Options(new double[]{-4, -3, -2, -1, 0, 1, 2, 3, 4}));
        sharedInfo.constraints().put("y",new Range(-3,3));
        sharedInfo.constraints().put("r",new Options(new double[]{1, 1.5, 2, 2.5, 3}));

        sharedInfo.history().add(new AttemptInfo("1","1","1",true,"hit", Duration.ZERO, LocalDateTime.now()));
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

