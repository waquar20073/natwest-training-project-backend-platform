package tk.bankofapisgroup6.userservices.recordtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.userservices.accounts.Account;
import tk.bankofapisgroup6.userservices.accounts.AccountService;

@Service
@AllArgsConstructor
public class RecordTransactionService {
	@Autowired
	RecordTransactionRepository recordTransactionRepository;
	@Autowired
	AccountService accountService;
	
	public boolean recordTransaction(RecordTransactionRequest request) {

		RecordTransactionRequest record = new RecordTransactionRequest(request.getAuthorId(), request.getSourceAccountId(), request.getSourceBankName(), request.getDestinationAccontId(), request.getDestinationBankName(), request.getAmount());
		recordTransactionRepository.save(record);
		return true;
	}
}
