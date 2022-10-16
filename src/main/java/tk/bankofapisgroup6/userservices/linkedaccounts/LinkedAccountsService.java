package tk.bankofapisgroup6.userservices.linkedaccounts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.userservices.accounts.Account;
import tk.bankofapisgroup6.userservices.accounts.AccountService;
import tk.bankofapisgroup6.userservices.util.JwtUtil;

@Service
@AllArgsConstructor
public class LinkedAccountsService {
	private AccountService accountService;
	private LinkedAccountsRepository linkedAccountsRepository;
	private LinkedBankRepository linkedBankRepository;
	private JwtUtil jwtUtil;
	
	
	public LinkedAccount storeAccountLink(String username, String bankName, String accessToken, String refreshToken) {
		Logger logger = LoggerFactory.getLogger(LinkedAccountsService.class);
		
		LinkedAccount linkedAccount;
		try {
			Account account = (Account)accountService.loadUserByUsername(username);
			 LocalDateTime timeNow = LocalDateTime.now(); 
			linkedAccount = new LinkedAccount(account,bankName,accessToken,refreshToken,timeNow.plusHours(24),timeNow.plusHours(24));
			linkedAccountsRepository.save(linkedAccount);
		}catch(Exception e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
//		logger.info(linkedAccount.toString());
		return linkedAccount;
	}


	public List<LinkedAccountResponse> getAccounts(long accountId) {
		Account account = (Account)accountService.loadUserById(accountId);
		List<LinkedAccount> linkedAccounts = linkedAccountsRepository.findByPlatformAccountId(account);
		List<LinkedBanks> banksList = linkedBankRepository.findAll();
		List<LinkedAccountResponse> response = new ArrayList<>();
		for(LinkedBanks bank: banksList) {
			System.out.println(bank.toString());
			boolean found=false;
			String accessToken="";
			for(LinkedAccount linkedAcc : linkedAccounts) {
				System.out.println(linkedAcc.toString());
				if(linkedAcc.getBankname().equals(bank.getBankname())) {
					found=true;
					accessToken=linkedAcc.getAccessToken();
					break;
					// TODO: check if access token expired or not
				}
			}
			LinkedAccountResponse lar = new LinkedAccountResponse();
			lar.setId(bank.getId());
			if(accessToken.length()>0) {
				lar.setBankAccountId(jwtUtil.getIdFromToken(accessToken.substring(10,accessToken.length()-2)));
			}else {
				lar.setBankAccountId(0);
			}
			lar.setBankname(bank.getBankname());
			
			lar.setImg(bank.getImg());
			lar.setServerAddress(bank.getServerAddress());
			lar.setAccessToken(accessToken);
			
			if(found) {
				lar.setValue("true");
			}else {
				lar.setValue("false");
			}
			response.add(lar);
		}
		return response;
	}}
