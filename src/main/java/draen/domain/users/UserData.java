package draen.domain.users;

import draen.domain.attempts.AttemptInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class UserData {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<AttemptInfo> attempts;

    public static UserData create() {
        return new UserData(new ArrayList<>());
    }
}
