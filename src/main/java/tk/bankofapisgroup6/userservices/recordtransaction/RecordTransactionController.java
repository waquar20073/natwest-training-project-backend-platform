package tk.bankofapisgroup6.userservices.recordtransaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping(path="api/v1/record")
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class RecordTransactionController {
	@Autowired
	RecordTransactionService recordTransactionService;
	
	@PostMapping
	public ResponseEntity<?> recordTransaction(@RequestHeader Map<String, String> headers, @RequestBody RecordTransactionRequest request) {
		ResponseEntity<?> response = null;
		try {
			boolean added = recordTransactionService.recordTransaction(request);
			response = ResponseEntity.status(HttpStatus.OK).body("Record Added");
			response = ResponseEntity.status(HttpStatus.OK).body("Record Added");
		}catch(Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Record Added");
		}
		return response;
	}
}
