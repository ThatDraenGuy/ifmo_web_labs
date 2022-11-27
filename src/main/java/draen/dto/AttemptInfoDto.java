package draen.dto;

import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;

import java.time.ZonedDateTime;

public class AttemptInfoDto {
    private CoordInfo coords;
    private ShotInfo shot;
    private ZonedDateTime currTime;
}
