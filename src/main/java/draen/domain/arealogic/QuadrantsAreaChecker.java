package draen.domain.arealogic;


import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;
import draen.domain.quadrants.Quadrant;
import draen.domain.quadrants.QuadrantsInfo;
import lombok.AllArgsConstructor;

import java.time.Instant;
@AllArgsConstructor
public class QuadrantsAreaChecker implements AreaChecker{
    private final QuadrantsInfo quadrantsInfo;

    @Override
    public AttemptInfo check(CoordInfo coordInfo) {
        Instant startTime = Instant.now();
        if (coordInfo.getR() < 0) return AttemptInfo.fromHit(coordInfo, ShotInfo.create(false, "Invalid R value", startTime));
        for (Quadrant quadrant : quadrantsInfo.getQuadrants()) {
            if (quadrant.checkHit(coordInfo.getX(), coordInfo.getY(), coordInfo.getR())) {
                return AttemptInfo.fromHit(coordInfo, ShotInfo.create(true, "That's a hit!", startTime));
            }
        }
        return AttemptInfo.fromHit(coordInfo, ShotInfo.create(false, "That's a miss!", startTime));
    }
}
