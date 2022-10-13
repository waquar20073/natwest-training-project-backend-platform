package tk.bankofapisgroup6.userservices.accounts;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.userservices.registration.token.ConfirmationToken;
import tk.bankofapisgroup6.userservices.registration.token.ConfirmationTokenService;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService{
	private final static String USER_NOT_FOUND_MSG = "user with username %s not found";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountRepository accountRepository; 
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return (UserDetails) accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)));
    }
    

    public String signUpUser(Account account) {
        boolean userExists = accountRepository
                .findByEmail(account.getEmail())
                .isPresent();
        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new IllegalStateException("email already taken");
        }
        
        boolean usernameExists = accountRepository
                .findByUsername(account.getUsername())
                .isPresent();
        if (usernameExists) {
            throw new IllegalStateException("username already taken");
        }
        
        String encodedPassword = bCryptPasswordEncoder
                .encode(account.getPassword());

        account.setPassword(encodedPassword);

        accountRepository.save(account);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public String loginUser(String username, String password) {
    	boolean usernameExists = accountRepository
                .findByUsername(username)
                .isPresent();
        if (!usernameExists) {
            throw new IllegalStateException("username not registered");
        }
        
        Account user = accountRepository.findByUsername(username).get();
        if(!user.isEnabled()) {
        	throw new IllegalStateException("account not enabled yet");
        }
        if(bCryptPasswordEncoder.matches(password,user.getPassword())) {
        	/** credentials match */
        	return "authenticated";			
        }
    	return "authentication failed";
    }
    
    public int enableAppUser(String email) {
        return accountRepository.enableAccount(email);
    }
}
