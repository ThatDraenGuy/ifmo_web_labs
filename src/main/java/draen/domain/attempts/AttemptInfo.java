package draen.domain.attempts;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;

@Entity
@Table(name = "attempts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttemptInfo {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private CoordInfo coords;

    @Embedded
    private ShotInfo shot;

    private ZonedDateTime currTime;


    public static AttemptInfo fromHit(CoordInfo coordInfo, ShotInfo shotInfo) {
        return new AttemptInfo(null, coordInfo, shotInfo, ZonedDateTime.now());
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