package tk.bankofapisgroup6.userservices.linkedaccounts;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class LinkedAccountResponse {
	long id;
	long bankAccountId;
    String bankname;
    String value;
    String img;
    String serverAddress;
    String accessToken;
}
