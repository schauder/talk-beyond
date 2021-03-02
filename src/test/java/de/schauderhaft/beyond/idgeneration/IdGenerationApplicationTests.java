package de.schauderhaft.beyond.idgeneration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class IdGenerationApplicationTests {

	@Autowired
	MinionAutoIdRepository minions;

	@Autowired
	JdbcAggregateTemplate template;

	@Test
	void saveWithNewIdFromDb() {

		MinionAutoId before = new MinionAutoId("Bob");
		assertThat(before.id).isNull();

		MinionAutoId after = minions.save(before);

		assertThat(after.id).isNotNull();
	}

	@Test
	void itsNewTrustMeIknowWhatImDoing() {

		MinionAutoId before = new MinionAutoId("Stuart");
		before.id = 42L;

		// We can't save this because Spring Data JDBC thinks it has to do an update.
		Assertions.assertThatThrownBy(() -> minions.save(before)).getRootCause().isInstanceOf(IncorrectUpdateSemanticsDataAccessException.class);

		template.insert(before);

		// It's saved!
		MinionAutoId reloaded = minions.findById(42L).get();
		assertThat(reloaded.name).isEqualTo("Stuart");
	}

}
