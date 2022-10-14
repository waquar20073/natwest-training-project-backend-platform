package tk.bankofapisgroup6.userservices.accounts;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final AccountRepository accountRepository; 
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return (UserDetails) accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)));
    }
    
    public UserDetails loadUserById(long accountId)
            throws UsernameNotFoundException {
        return (UserDetails) accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, accountId)));
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

    public HashMap<String, String> loginUser(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
    	boolean usernameExists = accountRepository
                .findByUsername(username)
                .isPresent();
        if (!usernameExists) {
            throw new IllegalStateException("username not registered");
        }
        
        // todo: check if account enabled
        
        Account user = accountRepository.findByUsername(username).get();
        if(! user.isEnabled()) {
        	throw new IllegalStateException("account not enabled yet");
        }
        if(bCryptPasswordEncoder.matches(password,user.getPassword())) {
        	/** credentials match */

            map.put("status", "authenticated");
            map.put("accountId", Long.toString(user.getId()));
        	return map;
        }
        map.put("status", "authentication failed");
        map.put("accountId", Long.toString(user.getId()));
        return map;
    }
    
    public int enableAppUser(String email) {
        return accountRepository.enableAccount(email);
    }
}
