package tk.bankofapisgroup6.userservices.accounts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService{
	
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	private final AccountRepository accountRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException{
		return accountRepository.findUserByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
}
