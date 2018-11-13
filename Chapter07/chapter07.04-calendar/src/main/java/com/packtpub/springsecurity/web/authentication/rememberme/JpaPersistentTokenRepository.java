package com.packtpub.springsecurity.web.authentication.rememberme;

import java.util.Date;
import java.util.List;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.packtpub.springsecurity.domain.PersistentLogin;
import com.packtpub.springsecurity.repository.RememberMeTokenRepository;

public class JpaPersistentTokenRepository implements PersistentTokenRepository {

	private RememberMeTokenRepository rememberMeTokenRepository;

	public JpaPersistentTokenRepository(RememberMeTokenRepository rmtr) {
		this.rememberMeTokenRepository = rmtr;
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		PersistentLogin newToken = new PersistentLogin(token);
		this.rememberMeTokenRepository.save(newToken);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		PersistentLogin token = this.rememberMeTokenRepository.findBySeries(series);
		if (token != null) {
			token.setToken(tokenValue);
			token.setLastUsed(lastUsed);
			this.rememberMeTokenRepository.save(token);
		}

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		PersistentLogin token = this.rememberMeTokenRepository.findBySeries(seriesId);
		PersistentRememberMeToken result = new PersistentRememberMeToken(token.getUsername(), token.getSeries(),
				token.getToken(), token.getLastUsed());
		return result;
	}

	@Override
	public void removeUserTokens(String username) {
		List<PersistentLogin> tokens = this.rememberMeTokenRepository.findByUsername(username);
		this.rememberMeTokenRepository.delete(tokens);
	}

}
