package info;

import com.fasterxml.jackson.annotation.JsonProperty;
import coordinates.Quadrant;

import java.time.Instant;
import java.util.List;

public record QuadrantsInfo(@JsonProperty List<Quadrant> quadrants) {

    public AttemptInfo check(Instant startTime, double x, double y, double r) {
        for (Quadrant quadrant : quadrants) {
            if (quadrant.checkHit(x,y,r)) {
                return AttemptInfo.fromHit(startTime,x,y,r,true,"That's a hit!");
            }
        }
        return AttemptInfo.fromHit(startTime,x,y,r,false,"LOL, that's a miss!");
    }
}
