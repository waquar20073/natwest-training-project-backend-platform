package tk.bankofapisgroup6.userservices.linkedaccounts;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.userservices.util.JwtUtil;


@RequestMapping("api/v1/linkaccount")
@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class LinkedAccountsController {
	@Autowired
	private JwtUtil jwutil;
	@Autowired
	private LinkedAccountsService linkedAccountsService;
	private static final Logger logger = LoggerFactory.getLogger(LinkedAccountsController.class);
	
	private boolean validateToken(Map<String, String> headers, long requestAccountId) {
		String accessToken = headers.get("authorization").substring(7);
		long accountId = jwutil.getIdFromToken(accessToken);
		return accountId==requestAccountId;
	}
	
	@PostMapping
	public ResponseEntity<?> linkAccount(@RequestBody LinkRequest request){
		ResponseEntity<?> response = null;
		try {
			String username = request.getUsername();
			String password = request.getPassword();
			String authenticationURL = request.getAuthenticationURL();
			String bankName = request.getBankname();
			String ownerUsername = request.getOwnerUsername();
			RestTemplate restTemplate = new RestTemplate();
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		    MultiValueMap<String, String> headers= new LinkedMultiValueMap<String, String>();
		    HashMap<String, String> map = new HashMap<String, String>();
		    map.put("Content-Type", "application/json");
		    headers.setAll(map);
		    HashMap<String, String> req_payload = new HashMap<String, String>();	    
		    req_payload.put("username", username);
		    req_payload.put("password", password);
		    HttpEntity<?> restRequest = new HttpEntity<>(req_payload, headers);
		    response = restTemplate.postForEntity( authenticationURL, request , String.class );
//	        ServiceResponse entityResponse = (ServiceResponse) response.getBody();
//	        logger.info(entityResponse.getData());
		    linkedAccountsService.storeAccountLink(ownerUsername, bankName, response.getBody().toString(), "");
		}catch(Exception e) {
			HashMap<String, String> map = new HashMap<>();
			map.put("token" , "");
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
		}
	    return response;
	}
	
	@PostMapping(path="listaccounts")
	public ResponseEntity<?> getLinkedAccounts(@RequestHeader Map<String, String> headers, @RequestBody AccountsRequest request){
		ResponseEntity<?> response = null;
		long accountId=request.getAccountId();
		System.out.println("\n\n\n\n"+String.valueOf(accountId));
		try {
//			if(!validateToken(headers,accountId)) {
//				throw new IllegalStateException("Token not valid");
//			}
			response = ResponseEntity.status(HttpStatus.OK).
					body(linkedAccountsService.getAccounts(accountId));
		}catch(Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return response;
	}
}
