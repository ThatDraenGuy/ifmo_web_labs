package quadrants;

import logic.quadrants.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuadrantsTest {

    @Test
    @DisplayName("Empty quadrant test")
    public void emptyQuadrantTest() {
        Quadrant quadrant = new EmptyQuadrant(1, 1);
        assertAll(
                "emptyQuadrant",
                () -> assertFalse(quadrant.checkHit(1, 1, 1)),
                () -> assertFalse(quadrant.checkHit(0, 0, 3)),
                () -> assertFalse(quadrant.checkHit(-3, -4, 10))
        );
    }

    @Test
    @DisplayName("Square quadrant test")
    public void squareQuadrantTest() {
        Quadrant quadrant1 = new SquareQuadrant(1, 1);
        Quadrant quadrant2 = new SquareQuadrant(0.5, -1);
        assertAll(
                "squareQuadrant",
                () -> assertTrue(quadrant1.checkHit(1, 1, 1)),
                () -> assertFalse(quadrant1.checkHit(-1, 1, 2)),
                () -> assertTrue(quadrant2.checkHit(2, -3, 4)),
                () -> assertFalse(quadrant2.checkHit(3, -3, 4))
        );
    }

    @Test
    @DisplayName("Triangle quadrant test")
    public void triangleQuadrantTest() {
        Quadrant quadrant1 = new TriangleQuadrant(1, 1);
        Quadrant quadrant2 = new TriangleQuadrant(-0.5, 1);
        assertAll(
                "triangleQuadrant",
                () -> assertTrue(quadrant1.checkHit(0.5, 0.5, 1)),
                () -> assertFalse(quadrant1.checkHit(1, 1, 1.5)),
                () -> assertTrue(quadrant2.checkHit(-1, 2, 4)),
                () -> assertFalse(quadrant2.checkHit(-1, 2.5, 4))
        );
    }

    @Test
    @DisplayName("Circle quadrant test")
    public void circleQuadrantTest() {
        Quadrant quadrant1 = new CircleQuadrant(1, 1);
        Quadrant quadrant2 = new CircleQuadrant(-1, -1);
        assertAll(
                "circleQuadrant",
                () -> assertTrue(quadrant1.checkHit(3, 4, 5)),
                () -> assertFalse(quadrant1.checkHit(3.5, 4, 5)),
                () -> assertTrue(quadrant2.checkHit(-4, -3, 5)),
                () -> assertFalse(quadrant2.checkHit(-4, 3, 5))
        );
    }
}
