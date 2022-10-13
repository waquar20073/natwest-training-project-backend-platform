package tk.bankofapisgroup6.userservices.registration;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	private String firstName;
	private String lastName;
	private String username;
	private String number;
	private String email;
	private String password;
	private Date dob;
	
}
