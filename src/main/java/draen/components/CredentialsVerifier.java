package draen.components;

import draen.dto.user.UserPostDto;
import draen.exceptions.CredentialsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CredentialsVerifier {
    @Value("${security.password.regexp}")
    private String passwordRegex;

    public void verify(UserPostDto userPostDto) throws CredentialsException {
        if (userPostDto.getUsername().length() < 4) throw new CredentialsException("Username is too short");
        if (! userPostDto.getPassword().matches(passwordRegex)) throw new CredentialsException("Password should be at least 8 characters long and contain at least 1 letter and 1 number");
    }
}
