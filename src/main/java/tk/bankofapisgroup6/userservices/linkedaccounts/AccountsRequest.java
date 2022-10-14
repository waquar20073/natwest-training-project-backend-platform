package tk.bankofapisgroup6.userservices.linkedaccounts;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class AccountsRequest {
	long accountId; // platform accountId

	public AccountsRequest() {
		super(); 
	}

	public AccountsRequest(long accountId) {
		super();
		this.accountId = accountId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
}
