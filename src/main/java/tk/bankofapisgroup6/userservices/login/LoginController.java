package tk.bankofapisgroup6.userservices.login;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/login")
@AllArgsConstructor
public class LoginController {
	
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginRequest request){
		ResponseEntity<HashMap<String, String>> response = null;
		HashMap<String, String> map = new HashMap<>();
		try {
			response = ResponseEntity.status(HttpStatus.OK).body(loginService.login(request));
		}catch(IllegalStateException exception) {
			map.put("status", exception.getLocalizedMessage());
			map.put("accountId", "-1");
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
		}
		return response;
	}
}
