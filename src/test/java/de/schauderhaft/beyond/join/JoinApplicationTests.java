package de.schauderhaft.beyond.join;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JoinApplicationTests {

	@Autowired
	MinionRepository minions;

	@Autowired
	PersonRepository persons;

	@Autowired
	JdbcAggregateTemplate template;

	@Test
	void loadWithJoin() {

		Person gru = persons.save(new Person("Gru"));

		Minion bob = minions.save(new Minion("Bob", AggregateReference.to(gru.id)));
		Minion kevin = minions.save(new Minion("Kevin", AggregateReference.to(gru.id)));
		Minion stuart = minions.save(new Minion("Stuart", AggregateReference.to(gru.id)));

		for (MinionView minion : minions.allMinionViews()) {
			System.out.println(minion.name + " - " + minion.master.name);
		}

	}


}
