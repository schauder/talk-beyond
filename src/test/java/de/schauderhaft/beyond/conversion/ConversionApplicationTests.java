package de.schauderhaft.beyond.conversion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

@SpringBootTest
class ConversionApplicationTests {

	@Autowired
	MinionRepository minions;

	@Autowired
	JdbcAggregateTemplate template;

	@Test
	void writeAndReadJson() {

		Minion prepareBob = new Minion("Bob");
		prepareBob.description.appearance.put("eye-color", "green, brown");
		prepareBob.description.appearance.put("number-of-eyes", "2");
		prepareBob.description.appearance.put("hair", "none");

		prepareBob.description.personality.put("character", "childish");
		prepareBob.description.personality.put("loves", "Poochy");
		prepareBob.description.personality.put("enjoys", "bedtime stories");
		prepareBob.description.personality.put("favorite-stuffed-animal", "Tim");

		Minion bob = minions.save(prepareBob);

		Minion bobReloaded = minions.findById(bob.id).get();

		System.out.println(bobReloaded);
	}


}
