package constraints;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= RadioOptions.class, name= RadioOptions.NAME),
        @JsonSubTypes.Type(value = CheckboxOptions.class, name = CheckboxOptions.NAME),
        @JsonSubTypes.Type(value= Range.class, name= Range.NAME)
})
public interface Constraint {
    boolean checkValue(double value);
}
