package draen.dto.attempt;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;
import draen.domain.attempts.ShotInfo;
import draen.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
public class AttemptInfoDto implements Dto<AttemptInfo> {
    private CoordInfo coords;
    private ShotInfo shot;
    private ZonedDateTime currTime;
}
