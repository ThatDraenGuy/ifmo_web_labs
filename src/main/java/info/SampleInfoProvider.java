package info;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SampleInfoProvider implements InfoProvider{
    private final String sample = """
                {
                  "quadrants":[
                    {
                      "type":"triangle",
                      "xMul":1,
                      "yMul":0.5
                    },
                    {
                      "type":"square",
                      "xMul":1,
                      "yMul":-1
                    },
                    {
                      "type":"circle",
                      "xMul":-0.5,
                      "yMul":-0.5
                    },
                    {
                      "type":"empty",
                      "xMul":-1,
                      "yMul":1
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
    public SharedInfo get() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(sample, SharedInfo.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
