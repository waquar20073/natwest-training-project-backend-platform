package tk.bankofapisgroup6.userservices.accounts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUsername(String username);
	Optional<Account> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Account a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAccount(String email);
    
    @Transactional
    @Modifying
    @Query("UPDATE Account a " +
            "SET a.password = ?2 WHERE a.id = ?1")
    int updatePassword(long accountId, String password);
    
    
}
