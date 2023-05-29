package monitoring;

import domain.AttemptInfo;
import domain.CoordInfo;
import lombok.Getter;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class ShotsMBeanImpl extends NotificationBroadcasterSupport implements ShotsMBean {
    @Getter
    private int totalShotNumber;
    @Getter
    private int missedShotNumber;

    public void addShot(AttemptInfo attemptInfo) {
        if (! attemptInfo.getShot().isRes())
                missedShotNumber++;
        totalShotNumber++;

        CoordInfo coords = attemptInfo.getCoords();
        double border = 4.0/3.0 * Math.abs(coords.getR());
        if (Math.abs(coords.getX()) > border || Math.abs(coords.getY()) > border) {
            Notification notification = new Notification("coordsOutOfGraph", this, totalShotNumber,
                    "Coords of shot are out of rendered graph");
            sendNotification(notification);
        }
    }
}
