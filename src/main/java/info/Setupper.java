package info;

import coordinates.*;

import java.util.ArrayList;
import java.util.List;

public class Setupper {


    public static void setup() {
        List<Quadrant> quadrants = new ArrayList<>();
        quadrants.add(new CircleQuadrant(1,1));
        quadrants.add(new SquareQuadrant(-1,1));
        quadrants.add(new TriangleQuadrant(-1,-1));
        quadrants.add(new EmptyQuadrant(1,-1));

    }


}
