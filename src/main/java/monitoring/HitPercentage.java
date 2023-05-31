package monitoring;

import domain.AttemptInfo;
import lombok.Getter;

public class HitPercentage implements HitPercentageMBean {
    private int totalShotNumber;
    private int hitShotNumber;
    @Getter
    private double hitPercentage;

    @Override
    public void addShot(AttemptInfo attemptInfo) {
        if (attemptInfo.getShot().isRes())
            hitShotNumber++;
        totalShotNumber++;
        hitPercentage = (double) (hitShotNumber) / (double) (totalShotNumber);
    }
}
