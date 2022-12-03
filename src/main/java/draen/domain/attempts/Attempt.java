package draen.domain.attempts;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.*;

//@Entity
//@Table(name = "attempts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Attempt {
//    @Setter
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;

    @Embedded
    private CoordInfo coords;

    @Embedded
    private ShotInfo shot;

    private ZonedDateTime currTime;

//    @ManyToOne
//    @JoinColumn(name="UserId")
//    private User user;


    public static Attempt fromHit(CoordInfo coordInfo, ShotInfo shotInfo) {
        return new Attempt(coordInfo, shotInfo, ZonedDateTime.now());
    }

    @Override
    public String toString() {
        return "AttemptInfo{" +
                "coordInfo=" + coords +
                ", shotInfo=" + shot +
                ", currTime=" + currTime +
                '}';
    }
}