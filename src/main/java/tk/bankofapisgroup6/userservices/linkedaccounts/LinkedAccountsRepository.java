package tk.bankofapisgroup6.userservices.linkedaccounts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tk.bankofapisgroup6.userservices.accounts.Account;

public interface LinkedAccountsRepository extends JpaRepository<LinkedAccount, Long>{

	@Query("SELECT la FROM LinkedAccount la WHERE la.platformAccountId=?1")
	List<LinkedAccount> findByPlatformAccountId(Account accountId);

}
