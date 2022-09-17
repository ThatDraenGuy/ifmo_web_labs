package constraints;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= Options.class, name= "options"),
        @JsonSubTypes.Type(value= Range.class, name= "range")
})
public interface Constraint {
    boolean checkValue(double value);
}
