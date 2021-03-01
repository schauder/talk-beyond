package de.schauderhaft.beyond.idgeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class IdGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdGenerationApplication.class, args);
	}

}
