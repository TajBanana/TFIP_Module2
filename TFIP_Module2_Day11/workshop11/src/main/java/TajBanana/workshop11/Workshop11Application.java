package TajBanana.workshop11;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@SpringBootApplication
public class Workshop11Application {
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {

		logger.info("app started");

		SpringApplication app = new SpringApplication(Workshop11Application.class);
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);

		String port = "";


		if (appArgs.containsOption("port")) {
			port = appArgs.getOptionValues("port").get(0);
		} else {
			port = DEFAULT_PORT;
		}

		app.setDefaultProperties(Collections.singletonMap("server.port",port));

		logger.info("Application started on port " +  port);

		app.run(args);
	}

}