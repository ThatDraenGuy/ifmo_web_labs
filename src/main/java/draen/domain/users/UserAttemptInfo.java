package draen.domain.users;

import draen.domain.attempts.AttemptInfo;
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
public class UserAttemptInfo {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int number;

    @Embedded
    private AttemptInfo attemptInfo;

    @ManyToOne
    @JoinColumn(name="UserId")
    private User user;

    public static UserAttemptInfo create(AttemptInfo attemptInfo, User user) {
        List<UserAttemptInfo> userAttempts = user.getData().getAttempts();
        UserAttemptInfo userAttemptInfo = new UserAttemptInfo(null, userAttempts.size(), attemptInfo, user);
        userAttempts.add(userAttemptInfo);
        return userAttemptInfo;
    }
}
