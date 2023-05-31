package monitoring;

import domain.AttemptInfo;

public interface HitPercentageMBean {
    double getHitPercentage();
    void addShot(AttemptInfo attemptInfo);
}
