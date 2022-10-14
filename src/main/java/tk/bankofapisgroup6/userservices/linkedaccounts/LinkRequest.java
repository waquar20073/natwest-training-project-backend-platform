package tk.bankofapisgroup6.userservices.linkedaccounts;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LinkRequest {
	String username;
	String password;
	String authenticationURL;
	String bankname;
	String ownerUsername;
}
