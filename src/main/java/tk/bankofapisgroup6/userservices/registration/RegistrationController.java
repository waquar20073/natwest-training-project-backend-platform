package tk.bankofapisgroup6.userservices.registration;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.bankofapisgroup6.userservices.registration.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
    	ResponseEntity<String> response = null;
    	try {
    		response =  ResponseEntity.status(HttpStatus.OK).body(registrationService.register(request));  
    	}catch(IllegalStateException exception) {
    		response =  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exception.getLocalizedMessage());
    	}
    	return response;
        
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
