package draen.rest.controllers;

import draen.components.QuadrantsInfoComponent;
import draen.domain.quadrants.QuadrantsInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuadrantsInfoController {
    private final QuadrantsInfoComponent quadrantsInfoComponent;

    @GetMapping("/quadrants")
    public ResponseEntity<QuadrantsInfo> get() {
        return ResponseEntity.ok(quadrantsInfoComponent.getQuadrantsInfo());
    }
}
