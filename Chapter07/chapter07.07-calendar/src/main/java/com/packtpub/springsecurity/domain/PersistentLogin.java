package com.packtpub.springsecurity.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogin implements Serializable {
	@Id
	private String series;
	private String username;
	private String token;

	private Date lastUsed;

	public PersistentLogin( ) { 
	}
	public PersistentLogin(String serise, String username, String token, Date lastUsed) {
		super();
		this.series = serise;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}
	public PersistentLogin(PersistentRememberMeToken token) {
		super();
		this.series = token.getSeries();
		this.username = token.getUsername() ;
		this.token = token.getTokenValue();
		this.lastUsed = token.getDate();
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLastUsed() {
		return lastUsed;
	}
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	
}
