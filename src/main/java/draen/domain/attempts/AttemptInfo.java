package draen.domain.attempts;


import javax.persistence.*;

import draen.domain.users.User;
import draen.domain.users.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;

//@Entity
//@Table(name = "attempts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AttemptInfo {
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


    public static AttemptInfo fromHit(CoordInfo coordInfo, ShotInfo shotInfo) {
        return new AttemptInfo(coordInfo, shotInfo, ZonedDateTime.now());
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