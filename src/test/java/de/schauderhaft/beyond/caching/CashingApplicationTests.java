package de.schauderhaft.beyond.caching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CashingApplicationTests {

	@Autowired
	MinionRepository minions;

	@Autowired
	ColorRepository colors;

	@BeforeEach
	void setup() {
		Color yellow = colors.save(new Color("Yellow"));
		Color purple = colors.save(new Color("Purple"));

		minions.save(new Minion("Bob", 2, yellow));
		minions.save(new Minion("Carl", 2, yellow));
		minions.save(new Minion("Dave", 2, yellow));
		minions.save(new Minion("Mark", 2, yellow));
		minions.save(new Minion("Tim", 2, yellow));
		minions.save(new Minion("Stuart", 1, yellow));
		minions.save(new Minion("Phil", 1, yellow));
		minions.save(new Minion("John", 1, yellow));

		minions.save(new Minion("Kevin", 2, purple));
		minions.save(new Minion("Jerry", 2, purple));
		minions.save(new Minion("Tom", 2, purple));
		minions.save(new Minion("Mike", 2, purple));
	}

	@Test
	void saveAndLoad() {

		Iterable<Minion> allMinions = this.minions.findAll();

		MultiValueMap<String, Minion> minionByColorName = new LinkedMultiValueMap<>();

		allMinions.forEach(m -> minionByColorName.add(colors.findById(m.color.getId()).get().name, m));

		assertThat(minionByColorName).containsKeys("Yellow", "Purple");
		assertThat(minionByColorName.get("Yellow")).hasSize(8);
		assertThat(minionByColorName.get("Purple")).hasSize(4);
	}

}
