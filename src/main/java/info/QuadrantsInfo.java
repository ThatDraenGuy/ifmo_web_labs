package info;

import com.fasterxml.jackson.annotation.JsonProperty;
import coordinates.Quadrant;
import exceptions.ValueException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record QuadrantsInfo(@JsonProperty List<Quadrant> quadrants) {

    public AttemptInfo check(Instant startTime, double x, double y, double r) {
        for (Quadrant quadrant : quadrants) {
            if (quadrant.checkHit(x,y,r)) {
                return AttemptInfo.fromHit(startTime,x,y,r,true,"That's a hit!");
            }
        }
        return AttemptInfo.fromHit(startTime,x,y,r,false,"LOL, that's a miss!");
    }

    public void update(Map<String, String[]> parameters) throws ValueException {
        List<Quadrant> newQuadrants = new ArrayList<>(4);
        for (int i=1; i<=4; i++) {
            newQuadrants.add(parse(i, parameters));
        }
        quadrants.clear();
        quadrants.addAll(newQuadrants);
    }

    private Quadrant parse(int num, Map<String, String[]> parameters) throws ValueException {
        try {
            String type = parameters.get("quadrants[" + num + "][type]")[0];
            String xMulParam = parameters.get("quadrants[" + num + "][x_mul]")[0];
            String yMulParam = parameters.get("quadrants[" + num + "][y_mul]")[0];
            double xMul = Double.parseDouble(xMulParam);
            double yMul = Double.parseDouble(yMulParam);
            return Quadrant.of(type, xMul, yMul);
        } catch (Exception e) {
            throw new ValueException(e.getMessage());
        }
    }
}
