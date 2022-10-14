package tk.bankofapisgroup6.userservices.recordtransaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tk.bankofapisgroup6.userservices.accounts.Account;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@ToString
public class RecordTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long recordId;
	@ManyToOne
    @JoinColumn(
            nullable = false,
            name = "account_id"
    )
	Account author;
	long sourceAccountId;
	String sourceBankName;
	long destinationAccontId;
	String destinationBankName;
	double amount;
	@CreationTimestamp
	Date timestamp;
	public RecordTransaction(Account author, long sourceAccountId, String sourceBankName, long destinationAccontId,
			String destinationBankName, double amount) {
		super();
		this.author = author;
		this.sourceAccountId = sourceAccountId;
		this.sourceBankName = sourceBankName;
		this.destinationAccontId = destinationAccontId;
		this.destinationBankName = destinationBankName;
		this.amount = amount;
	}
	
}
