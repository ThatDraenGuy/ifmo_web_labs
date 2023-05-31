package monitoring;

import java.time.Duration;
import java.time.Instant;

public class Interval implements IntervalMBean {
    private long averageInterval = 0;
    private int clicksNumber = 0;
    private Instant lastClick = Instant.now();
    @Override
    public void handleClick() {
        Instant click = Instant.now();
        Duration intervalDuration = Duration.between(lastClick, click);
        long interval = intervalDuration.toNanos();

        averageInterval = ( averageInterval * clicksNumber + interval ) / ++clicksNumber;
        lastClick = click;
    }
    @Override
    public long getAverageInterval() {
        return averageInterval;
    }
}
