package com.packtpub.springsecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
 
  
@SpringBootApplication
@Import({ TestConfig.class  })
public class TestApplication {

	private static final Logger logger = LoggerFactory.getLogger(TestApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}