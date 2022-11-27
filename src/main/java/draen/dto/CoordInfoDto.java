package draen.dto;

import draen.domain.attempts.CoordInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoordInfoDto implements Dto<CoordInfo> {
    private double x;
    private double y;
    private double r;
}
