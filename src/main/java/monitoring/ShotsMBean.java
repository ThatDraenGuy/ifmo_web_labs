package monitoring;

import domain.AttemptInfo;

public interface ShotsMBean {
    int getTotalShotNumber();
    int getMissedShotNumber();
    void addShot(AttemptInfo attemptInfo);
}
