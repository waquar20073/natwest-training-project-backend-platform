package tk.bankofapisgroup6.userservices.recordtransaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordTransactionRepository extends JpaRepository<RecordTransactionRequest, Long>{


}
