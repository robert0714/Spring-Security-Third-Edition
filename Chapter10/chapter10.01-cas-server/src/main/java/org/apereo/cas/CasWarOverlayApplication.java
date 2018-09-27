package org.apereo.cas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

//import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
//import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication  
public class CasWarOverlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasWarOverlayApplication.class, args);
	}
	
 
}
