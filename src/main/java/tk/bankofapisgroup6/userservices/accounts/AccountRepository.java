package tk.bankofapisgroup6.userservices.accounts;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface AccountRepository {
	Optional<Account> findUserByEmail(String email);
}
