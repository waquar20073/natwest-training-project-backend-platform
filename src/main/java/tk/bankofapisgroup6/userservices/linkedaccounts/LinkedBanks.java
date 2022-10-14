package tk.bankofapisgroup6.userservices.linkedaccounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class LinkedBanks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String bankname;
	String img;
	String serverAddress;
}
