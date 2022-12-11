package draen.rest.controllers.authorized;

import draen.dto.user.UserGetDto;
import draen.rest.controllers.UserControllerUtils;
import draen.security.AppUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizedUserController {
    private final UserControllerUtils utils;
    @GetMapping("/id/{userId}")
    public ResponseEntity<UserGetDto> userById(@PathVariable long userId) {
        return utils.getWrapper().wrapOk(utils.getUserOr(userId), UserGetDto.class);
    }

    @PostMapping("/id/{userId}/logout")
    public ResponseEntity<?> logout(@PathVariable String userId) {
        ResponseCookie cookie = ResponseCookie.from("token", null).path("/api").httpOnly(true).maxAge(0).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }


    @GetMapping("/current")
    public ResponseEntity<?> getCurrent(@AuthenticationPrincipal AppUserDetails appUserDetails) {
        return utils.getWrapper().wrapOk(utils.getUserOr(appUserDetails.getUsername()), UserGetDto.class);
    }
}
