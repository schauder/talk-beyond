package de.schauderhaft.beyond.bidirectional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
class BidirectionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidirectionalApplication.class, args);
	}

}
