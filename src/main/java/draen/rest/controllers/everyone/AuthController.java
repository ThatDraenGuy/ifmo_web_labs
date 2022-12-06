package draen.rest.controllers.everyone;

import draen.domain.users.User;
import draen.dto.user.UserGetDto;
import draen.dto.user.UserPostDto;
import draen.exceptions.DtoException;
import draen.rest.Wrapper;
import draen.rest.controllers.UserControllerUtils;
import draen.rest.response.LoginResponse;
import draen.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserControllerUtils utils;
    private final Wrapper wrapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserPostDto userPostDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userPostDto.getUsername(), userPostDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserGetDto userGetDto = wrapper.wrap(utils.getUserOr(authentication.getName()), UserGetDto.class);
        return ResponseEntity.ok(new LoginResponse(token, userGetDto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserPostDto userPostDto) {
        try {
            User user = utils.getRepository().save(wrapper.unwrap(userPostDto, User.class));
            return ResponseEntity.ok(null);
        } catch (DtoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
        }
    }
}