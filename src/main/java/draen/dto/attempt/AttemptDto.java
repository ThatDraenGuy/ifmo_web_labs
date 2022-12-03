package draen.dto.attempt;

import draen.domain.attempts.Attempt;
import draen.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
public class AttemptDto implements Dto<Attempt> {
    private CoordInfoDto coords;
    private ShotInfoDto shot;
    private ZonedDateTime currTime;
}
