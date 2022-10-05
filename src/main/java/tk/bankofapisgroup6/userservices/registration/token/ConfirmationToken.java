package tk.bankofapisgroup6.userservices.registration.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.bankofapisgroup6.userservices.accounts.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "account_id"
    )
    private Account account;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             Account account) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.account = account;
    }
}