package com.packtpub.springsecurity.web.authentication.rememberme;

import java.util.Date;
import java.util.List;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.packtpub.springsecurity.domain.PersistentLogin;
import com.packtpub.springsecurity.repository.RememberMeTokenRepository;

public class JpaTokenRepositoryCleaner implements Runnable {

	private final RememberMeTokenRepository repository;
	private final long tokenValidityInMs;

	public JpaTokenRepositoryCleaner(RememberMeTokenRepository rmtr, long tokenValidityInMs) {
		if (rmtr == null) {
			throw new IllegalArgumentException("jdbcOperations cannot be null");
		}

		if (tokenValidityInMs < 1) {
			throw new IllegalArgumentException("tokenValidityInMs must be greater than 0. Got " + tokenValidityInMs);
		}
		this.repository = rmtr;
		this.tokenValidityInMs = tokenValidityInMs;
	}

	@Override
	public void run() {
		long expiredInMs = System.currentTimeMillis() - tokenValidityInMs;
		
		try {
			Iterable<PersistentLogin> expired = this.repository.findByLastUsed(new Date(expiredInMs));
			
			for(PersistentLogin pl :expired) {
				 this.repository.delete(pl);			 
			}
		} catch (Throwable e) { 
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
