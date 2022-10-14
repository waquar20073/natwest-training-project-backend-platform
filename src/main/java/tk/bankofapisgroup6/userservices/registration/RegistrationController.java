package tk.bankofapisgroup6.userservices.registration;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.bankofapisgroup6.userservices.registration.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@CrossOrigin(origins="*", allowedHeaders = "*")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
    	ResponseEntity<String> response = null;
    	try {
    		logger.info("\n\n\n\n\n\nHehrehrherhehre");
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
