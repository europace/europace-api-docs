package de.europace.swaggeraggregator;

import de.europace.swaggeraggregator.model.SpecSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SpecSourceProperties.class)
public class SwaggerAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerAggregatorApplication.class, args);
	}

}
