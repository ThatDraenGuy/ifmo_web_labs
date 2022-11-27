package draen.domain.arealogic;

import draen.domain.attempts.AttemptInfo;
import draen.domain.attempts.CoordInfo;

public interface AreaChecker {
    AttemptInfo check(CoordInfo coordInfo);
}
