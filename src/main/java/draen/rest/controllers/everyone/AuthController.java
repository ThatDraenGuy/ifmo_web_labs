package draen.rest.controllers.everyone;

import draen.domain.users.User;
import draen.dto.user.UserGetDto;
import draen.dto.user.UserPostDto;
import draen.exceptions.DtoException;
import draen.rest.Wrapper;
import draen.rest.controllers.UserControllerUtils;
import draen.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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

        ResponseCookie cookie = ResponseCookie.from("token", token).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();

        UserGetDto userGetDto = wrapper.wrap(utils.getUserOr(authentication.getName()), UserGetDto.class);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(userGetDto);
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

    @GetMapping("/exists/{username}")
    public ResponseEntity<?> usernameExists(@PathVariable String username) {
        return ResponseEntity.ok(utils.getRepository().existsByUsername(username));
    }
}
