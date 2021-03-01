package de.schauderhaft.beyond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BeyondApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeyondApplication.class, args);
	}

}
