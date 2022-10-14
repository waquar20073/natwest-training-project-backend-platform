package tk.bankofapisgroup6.userservices.updatepassword;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.bankofapisgroup6.userservices.util.JwtUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping(path="updatepassword")
public class UpdatePasswordController {
	@Autowired
	private JwtUtil jwutil;
	@Autowired
	private UpdatePasswordService updatePasswordService;
	private static final Logger logger = LoggerFactory.getLogger(UpdatePasswordController.class);
	
	private boolean validateToken(Map<String, String> headers, long requestAccountId) {
		String accessToken = headers.get("authorization").substring(7);
		long accountId = jwutil.getIdFromToken(accessToken);
		return accountId==requestAccountId;
	}
	
	@PostMapping
	public ResponseEntity<String> updatePassword(@RequestHeader Map<String, String> headers,@RequestBody Request request){
		ResponseEntity<String> response = null;
		try {
			if(!validateToken(headers,request.getAccountId())) {
				throw new IllegalStateException("Token not valid");
			}
			response = ResponseEntity.status(HttpStatus.OK).
					body(updatePasswordService.updatePassword(request.getAccountId(), request.getOldPassword(), request.getNewPassword()));
		}catch(Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
	
	@GetMapping("otp")
	public ResponseEntity<String> getOtp(@RequestHeader Map<String, String> headers, @RequestBody Request request){
		
		return null;
	}
	@PostMapping("reset")
	public ResponseEntity<String> resetPassword(@RequestHeader Map<String, String> headers,@RequestBody ResetRequest request){
		if(!validateToken(headers,request.getAccountId())) {
			throw new IllegalStateException("Token not valid");
		}
		return null;
	}
}
