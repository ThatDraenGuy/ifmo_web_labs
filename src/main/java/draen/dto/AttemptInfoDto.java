package draen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AttemptInfoDto {
    private Long id;
    private CoordInfoDto coords;
    private ShotInfoDto shot;
    private ZonedDateTime currTime;
    private Long userId;
}
