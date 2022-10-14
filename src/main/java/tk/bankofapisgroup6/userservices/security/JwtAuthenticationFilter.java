package tk.bankofapisgroup6.userservices.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.userservices.accounts.Account;
import tk.bankofapisgroup6.userservices.accounts.AccountService;
import tk.bankofapisgroup6.userservices.registration.RegistrationController;
import tk.bankofapisgroup6.userservices.util.JwtUtil;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private AccountService accountService;
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get header
		String requestTokenHeader = request.getHeader("authorization");
		String jwtToken=null;
		//check format
		if(requestTokenHeader!=null && !requestTokenHeader.equals("Bearer undefined") && requestTokenHeader.startsWith("Bearer ")) {
			long accountId=0;
			jwtToken=requestTokenHeader.substring(7);
			try {
				accountId = jwtutil.getIdFromToken(jwtToken);
			}catch(Exception e) {
				e.printStackTrace();
			}
			Account userDetails=null;
			try {
				userDetails = (Account) accountService.loadUserById(accountId);
					
			}catch(Exception e) {
				logger.info(e.getLocalizedMessage());
			}
			if(accountId!=0 && SecurityContextHolder.getContext().getAuthentication()==null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				throw new IllegalStateException("Token is not validated");
			}
		}
		filterChain.doFilter(request, response);
	}

}
