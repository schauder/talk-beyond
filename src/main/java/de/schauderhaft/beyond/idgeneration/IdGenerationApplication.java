package de.schauderhaft.beyond.idgeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;

import java.util.UUID;

@EnableCaching
@SpringBootApplication
class IdGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdGenerationApplication.class, args);
	}

	@Bean
	BeforeSaveCallback<StringIdMinion> beforeSaveCallback() {

		return (minion, mutableAggregateChange) -> {
			minion.id = UUID.randomUUID().toString();
			return minion;
		};
	}

}
