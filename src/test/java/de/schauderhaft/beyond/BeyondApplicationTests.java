package de.schauderhaft.beyond;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static de.schauderhaft.beyond.Color.*;

@SpringBootTest
class BeyondApplicationTests {

	@Autowired
	MinionRepository repository;


	@Test
	void saveAndLoad() {

		Minion bob = new Minion("Bob", 2, YELLOW);

		repository.save(bob);
	}

}
