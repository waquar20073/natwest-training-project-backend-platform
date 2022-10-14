package tk.bankofapisgroup6.userservices.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.userservices.accounts.Account;
import tk.bankofapisgroup6.userservices.accounts.AccountService;

@RequestMapping(path="api/v1/jwt")
@AllArgsConstructor
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
public class JwtController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtUtil jwtUtil;
	private static Logger logger = LoggerFactory.getLogger(JwtController.class);
	
	@PostMapping(path="token")
	public ResponseEntity<?> generateToken(@RequestBody JwtREquest jwtrequest){
		Account account = null;
		try {
			long accountId = Long.parseLong(accountService.loginUser(jwtrequest.getUsername(), jwtrequest.getPassword()).get("accoundId"));
			account = (Account)accountService.loadUserById(accountId);
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new IllegalStateException("Invalid Credentials");
		}
		
		String token = jwtUtil.generateToken(account);
		logger.info("Token Generated");
		return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));
	}
}
