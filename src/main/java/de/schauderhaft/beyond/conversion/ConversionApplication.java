package de.schauderhaft.beyond.conversion;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.convert.JdbcValue;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.sql.JDBCType;

import static java.util.Arrays.*;

@EnableCaching
@SpringBootApplication
class ConversionApplication {

	public static final String JSON_APPEARANCE = "appearance";

	public static void main(String[] args) {
		SpringApplication.run(ConversionApplication.class, args);
	}

	@Configuration
	static class ConversionConfigurationApplication extends AbstractJdbcConfiguration {

		@Override
		@Bean
		public JdbcCustomConversions jdbcCustomConversions() {
			return new JdbcCustomConversions(asList(DescriptionToString.INSTANCE, StringToDescription.INSTANCE));
		}
	}

	@WritingConverter
	enum DescriptionToString implements Converter<Description, JdbcValue> {

		INSTANCE;

		@Override
		public JdbcValue convert(Description source) {

			JSONObject json = new JSONObject();
			json.put(JSON_APPEARANCE, new JSONObject(source.appearance));
			json.put("personality", new JSONObject(source.personality));

			System.out.println("Json to be written to database: "  + json.toString());

			return JdbcValue.of(json.toString(), JDBCType.VARCHAR);
		}
	}

	@ReadingConverter
	enum StringToDescription implements Converter<String, Description> {

		INSTANCE;

		@Override
		public Description convert(String jsonString) {

			System.out.println(jsonString);
			JSONObject json = new JSONObject(jsonString);
			Description description = new Description();
			json.getJSONObject(JSON_APPEARANCE).toMap().forEach((k,v) -> description.appearance.put(k, v.toString()));
			json.getJSONObject("personality").toMap().forEach((k,v) -> description.personality.put(k, v.toString()));

			return description;
		}
	}

}
