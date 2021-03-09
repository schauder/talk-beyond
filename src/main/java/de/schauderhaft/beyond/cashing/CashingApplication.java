package de.schauderhaft.beyond.cashing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
class CashingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashingApplication.class, args);
	}

}
