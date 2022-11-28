package draen.dto.attempt;

import draen.domain.attempts.CoordInfo;
import draen.dto.Dto;
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
