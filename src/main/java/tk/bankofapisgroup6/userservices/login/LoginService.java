package tk.bankofapisgroup6.userservices.login;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.userservices.accounts.AccountService;
import tk.bankofapisgroup6.userservices.registration.RegistrationRequest;

@Service
@AllArgsConstructor
public class LoginService {
	
	private AccountService accountService;
	
	public String login(LoginRequest request) {
		// Todo: Validate username and password
		return accountService.loginUser(request.getUsername(), request.getPassword());
	}
}