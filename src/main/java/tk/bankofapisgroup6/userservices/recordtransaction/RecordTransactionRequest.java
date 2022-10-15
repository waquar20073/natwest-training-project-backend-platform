package tk.bankofapisgroup6.userservices.recordtransaction;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tk.bankofapisgroup6.userservices.accounts.Account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class RecordTransactionRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	long authorId;
	long sourceAccountId;
	String sourceBankName;

	public RecordTransactionRequest(long authorId, long sourceAccountId, String sourceBankName, long destinationAccontId, String destinationBankName, double amount) {
		this.authorId = authorId;
		this.sourceAccountId = sourceAccountId;
		this.sourceBankName = sourceBankName;
		this.destinationAccontId = destinationAccontId;
		this.destinationBankName = destinationBankName;
		this.amount = amount;
	}

	long destinationAccontId;
	String destinationBankName;
	double amount;
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public long getSourceAccountId() {
		return sourceAccountId;
	}
	public void setSourceAccountId(long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}
	public String getSourceBankName() {
		return sourceBankName;
	}
	public void setSourceBankName(String sourceBankName) {
		this.sourceBankName = sourceBankName;
	}
	public long getDestinationAccontId() {
		return destinationAccontId;
	}
	public void setDestinationAccontId(long destinationAccontId) {
		this.destinationAccontId = destinationAccontId;
	}
	public String getDestinationBankName() {
		return destinationBankName;
	}
	public void setDestinationBankName(String destinationBankName) {
		this.destinationBankName = destinationBankName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
