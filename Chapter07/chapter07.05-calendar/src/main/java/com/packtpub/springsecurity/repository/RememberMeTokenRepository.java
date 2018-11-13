package com.packtpub.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packtpub.springsecurity.domain.PersistentLogin;

public interface RememberMeTokenRepository extends JpaRepository<PersistentLogin, String> {

	PersistentLogin findBySeries(String series);

	List<PersistentLogin> findByUsername(String username);
}
