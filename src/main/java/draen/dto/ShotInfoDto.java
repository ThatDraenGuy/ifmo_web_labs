package draen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
public class ShotInfoDto {
    private boolean res;
    private String message;
    private Duration execTime;
}
