package monitoring;

import domain.AttemptInfo;
import domain.CoordInfo;
import lombok.Getter;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Shots extends NotificationBroadcasterSupport implements ShotsMBean {
    @Getter
    private int totalShotNumber;
    @Getter
    private int missedShotNumber;

    public void addShot(AttemptInfo attemptInfo) {
        if (! attemptInfo.getShot().isRes())
                missedShotNumber++;
        totalShotNumber++;

        if  (totalShotNumber % 10 == 0) {
            Notification notification = new Notification("totalShotNumberDivisibleBy10", this, totalShotNumber,
                    "Number of shots is divisible by 10");
            sendNotification(notification);
        }
    }
}
