package quadrants;

import logic.QuadrantsBean;
import logic.quadrants.SquareQuadrant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuadrantsBeanTest {

    @Test
    @DisplayName("Quadrants bean test")
    public void quadrantsBeanTest() {
        QuadrantsBean quadrantsBean = new QuadrantsBean();
        quadrantsBean.setQuadrants(List.of(
                new SquareQuadrant(1, 1),
                new SquareQuadrant(1, -1),
                new SquareQuadrant(-1, 1),
                new SquareQuadrant(-1, -1)
        ));

        assertAll(
                "quadrantsBean",
                () -> assertAll(
                        () -> assertEquals(quadrantsBean.doCheck(2, -4.5, 1).getCoords().getX(), 2),
                        () -> assertEquals(quadrantsBean.doCheck(2, -4.5, 1).getCoords().getY(), -4.5),
                        () -> assertEquals(quadrantsBean.doCheck(2, -4.5, 1).getCoords().getR(), 1)
                ),
                () -> assertFalse(quadrantsBean.doCheck(1, 1, -1).getShot().isRes()),
                () -> assertFalse(quadrantsBean.doCheck(2, -2, 1).getShot().isRes()),
                () -> assertTrue(quadrantsBean.doCheck(-1, 1, 1).getShot().isRes()),
                () -> assertFalse(quadrantsBean.doCheck(3, -4, 5).getShot().getExecTime().isZero()),
                () -> assertFalse(quadrantsBean.doCheck(3, -4, 5).getShot().getExecTime().isNegative())
        );
    }
}
