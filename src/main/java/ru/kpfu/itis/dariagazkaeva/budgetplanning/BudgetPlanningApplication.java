package ru.kpfu.itis.dariagazkaeva.budgetplanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.config.RootConfig;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.config.SecurityConfig;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.config.WebConfig;

@Configuration
@EnableAutoConfiguration
@Import({RootConfig.class, SecurityConfig.class, WebConfig.class})
public class BudgetPlanningApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BudgetPlanningApplication.class);
		app.run(args);
	}

}
