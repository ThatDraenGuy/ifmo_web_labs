package coordinates;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CircleQuadrant.class, name = "circle"),
        @JsonSubTypes.Type(value = SquareQuadrant.class, name = "square"),
        @JsonSubTypes.Type(value = TriangleQuadrant.class, name = "triangle"),
        @JsonSubTypes.Type(value = EmptyQuadrant.class, name = "empty")
})
public interface Quadrant {
    boolean checkHit(double x, double y, double r);


    static Quadrant of(String name, double xMul, double yMul) {
        return switch (name) {
            case ("circle") -> new CircleQuadrant(xMul, yMul);
            case ("square") -> new SquareQuadrant(xMul, yMul);
            case ("triangle") -> new TriangleQuadrant(xMul, yMul);
            default -> new EmptyQuadrant(xMul, yMul);
        };
    }
}
