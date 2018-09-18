package com.packtpub.springsecurity.web.authentication.rememberme;

import java.util.Date;

import com.packtpub.springsecurity.domain.PersistentLogin;
import com.packtpub.springsecurity.repository.RememberMeTokenRepository;

public class JpaTokenRepositoryCleaner implements Runnable {
	private RememberMeTokenRepository rememberMeTokenRepository;
	private final long tokenValidityInMs;

	public JpaTokenRepositoryCleaner(RememberMeTokenRepository repository, long tokenValidityInMs) {
		if (repository == null) {
			throw new IllegalArgumentException("jdbcOperations cannot be null");
		}
		if (tokenValidityInMs < 1) {
			throw new IllegalArgumentException("tokenValidityInMs must be greater than 0. Got " + tokenValidityInMs);
		}
		this.rememberMeTokenRepository = repository;
		this.tokenValidityInMs = tokenValidityInMs;
	}

	public void run() {
		long expiredInMs = System.currentTimeMillis() - tokenValidityInMs;
		try {
			Iterable<PersistentLogin> expired = rememberMeTokenRepository.findByLastUsedAfter(new Date(expiredInMs));
			for (PersistentLogin pl : expired) {
				rememberMeTokenRepository.delete(pl);
			}
		} catch (Throwable t) {

		}
	}
}