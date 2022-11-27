package draen.dto;

import draen.domain.attempts.ShotInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
public class ShotInfoDto implements Dto<ShotInfo> {
    private boolean res;
    private String message;
    private Duration execTime;
}
