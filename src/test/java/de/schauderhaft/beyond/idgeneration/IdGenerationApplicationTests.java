package de.schauderhaft.beyond.idgeneration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class IdGenerationApplicationTests {

	@Autowired
	MinionRepository minions;

	@Autowired
	StringIdMinionRepository stringions;

	@Autowired
	JdbcAggregateTemplate template;

	@Test
	void saveWithNewIdFromDb() {

		Minion before = new Minion("Bob");
		assertThat(before.id).isNull();

		Minion after = minions.save(before);

		assertThat(after.id).isNotNull();
	}

	@Test
	void itsNewTrustMeIknowWhatImDoing() {

		Minion before = new Minion("Stuart");
		before.id = 42L;

		// We can't save this because Spring Data JDBC thinks it has to do an update.
		Assertions.assertThatThrownBy(() -> minions.save(before)).getRootCause().isInstanceOf(IncorrectUpdateSemanticsDataAccessException.class);

		template.insert(before);

		// It's saved!
		Minion reloaded = minions.findById(42L).get();
		assertThat(reloaded.name).isEqualTo("Stuart");
	}

	@Test
	void idByEventListener() {

		StringIdMinion before = new StringIdMinion("Kevin");

		stringions.save(before);

		assertThat(before.id).isNotNull();


		// It's saved!
		StringIdMinion reloaded = stringions.findById(before.id).get();
		assertThat(reloaded.name).isEqualTo("Kevin");
	}

}
