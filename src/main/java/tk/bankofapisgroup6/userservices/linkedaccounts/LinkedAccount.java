package tk.bankofapisgroup6.userservices.linkedaccounts;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tk.bankofapisgroup6.userservices.accounts.Account;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class LinkedAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@ManyToOne
    @JoinColumn(
            nullable = false,
            name = "account_id"
    )
	Account platformAccountId;
	String bankname;
	String accessToken;
	String refreshToken;
	
	@Column(nullable = false)
    private LocalDateTime accessExpiresAt;
	@Column(nullable = false)
    private LocalDateTime refreshExpiresAt;
	public LinkedAccount(Account platformAccountId, String bankname, String accessToken, String refreshToken,
			LocalDateTime accessExpiresAt, LocalDateTime refreshExpiresAt) {
		super();
		this.platformAccountId = platformAccountId;
		this.bankname = bankname;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessExpiresAt = accessExpiresAt;
		this.refreshExpiresAt = refreshExpiresAt;
	}
	
	
	
}
