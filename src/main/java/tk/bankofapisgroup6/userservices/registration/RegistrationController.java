package tk.bankofapisgroup6.userservices.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tk.bankofapisgroup6.userservices.registration.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
