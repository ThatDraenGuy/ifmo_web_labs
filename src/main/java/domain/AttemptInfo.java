package domain;


import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ui.TimeZoneBean;

import java.time.*;

@Entity
@Table(name = "attempts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttemptInfo {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coord_id", referencedColumnName = "id")
    private CoordInfo coords;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shot_id", referencedColumnName = "id")
    private ShotInfo shot;

    private ZonedDateTime currTime;


    public static AttemptInfo fromHit(CoordInfo coordInfo, ShotInfo shotInfo) {
//        String timezone = CDI.current().select(TimeZoneBean.class).get().getTimezone();
        return new AttemptInfo(null, coordInfo, shotInfo, ZonedDateTime.now(ZoneId.of("America/New_York")));
    }

    @Override
    public String toString() {
        return "AttemptInfo{" +
                "id=" + id +
                ", coordInfo=" + coords +
                ", shotInfo=" + shot +
                ", currTime=" + currTime +
                '}';
    }
}
