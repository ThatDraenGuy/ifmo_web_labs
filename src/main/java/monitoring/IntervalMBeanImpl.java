package monitoring;

import lombok.Getter;

import java.time.Duration;
import java.time.Instant;

public class IntervalMBeanImpl implements IntervalMBean {
    @Getter
    private long averageInterval;
    private int clicksNumber;
    private Instant lastClick;

    public void handleClick() {
        Instant click = Instant.now();
        Duration intervalDuration = Duration.between(lastClick, click);
        long interval = intervalDuration.toNanos();

        averageInterval = ( averageInterval * clicksNumber + interval ) / ++clicksNumber;
        lastClick = click;
    }
}
