package domain.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SampleAppInfoProvider implements AppInfoProvider {
    @Override
    public AppInfo get() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String sample = """
                    {
                        "quadrantsInfo":{
                            "quadrants":[
                                {
                                    "type":"square",
                                    "xMul":1,
                                    "yMul":0.5
                                },{
                                    "type":"square",
                                    "xMul":1,
                                    "yMul":-1
                                },{
                                    "type":"circle",
                                    "xMul":-0.5,
                                    "yMul":-0.5
                                },{
                                    "type":"empty",
                                    "xMul":-1,
                                    "yMul":1
                                }
                            ]
                        },
                        "constraintsInfo":{
                            "constraints":{
                                "x":{
                                    "type":"options",
                                    "options":[-4.0,-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0,4.0]
                                },
                                "y":{
                                    "type":"range",
                                    "min":-3.0,
                                    "max":3.0
                                },
                                "r":{
                                    "type":"options",
                                    "options":[1.0,1.5,2.0,2.5,3.0]
                                }
                            },
                            "isCheckingConstraints":false
                        }
                    }
                    """;
            return mapper.readValue(sample, AppInfo.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
