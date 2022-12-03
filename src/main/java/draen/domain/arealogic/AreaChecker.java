package draen.domain.arealogic;

import draen.domain.attempts.Attempt;
import draen.domain.attempts.CoordInfo;

public interface AreaChecker {
    Attempt check(CoordInfo coordInfo);
}
