package tk.bankofapisgroup6.userservices.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/login")
@AllArgsConstructor
public class LoginController {
	
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<String> login(@RequestBody LoginRequest request){
		ResponseEntity<String> response = null;
		try {
			response = ResponseEntity.status(HttpStatus.OK).body(loginService.login(request));
		}catch(IllegalStateException exception) {
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exception.getLocalizedMessage());
		}
		return response;
	}
}
