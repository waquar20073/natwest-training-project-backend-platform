package tk.bankofapisgroup6.userservices.updatepassword;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import tk.bankofapisgroup6.userservices.registration.token.ConfirmationToken;

public interface UpdatePasswordRepository extends JpaRepository<Otp, Long>{

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE Otp o " +
            "SET o.confirmedAt = ?2 " +
            "WHERE o.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
