package draen.domain.users;

import draen.domain.attempts.Attempt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attempts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAttempt {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int number;

    @Embedded
    private Attempt attempt;

    @ManyToOne
    @JoinColumn(name="UserId")
    private User user;

    public static UserAttempt create(Attempt attempt, User user) {
        List<UserAttempt> userAttempts = user.getData().getAttempts();
        UserAttempt userAttempt = new UserAttempt(null, userAttempts.size(), attempt, user);
//        userAttempts.add(userAttemptInfo);
        return userAttempt;
    }
}
