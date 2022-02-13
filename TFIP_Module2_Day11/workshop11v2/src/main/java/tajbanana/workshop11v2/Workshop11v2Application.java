package tajbanana.workshop11v2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Workshop11v2Application {
    private static final String DEFAULT_PORT = "3000";
    private static final Logger logger = LoggerFactory.getLogger(Workshop11v2Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Workshop11v2Application.class);
        ApplicationArguments appArgs = new DefaultApplicationArguments(args);

        List optionsValue = appArgs.getOptionValues("port");
        String portNumber = null;

        if (optionsValue == null || optionsValue.get(0) == null) {
            logger.info("no port detected, resolving to default port 3000");
            portNumber = DEFAULT_PORT;

        } else {
            logger.info("port input detected: " + optionsValue.get(0));
            portNumber = (String) optionsValue.get(0);
        }

        app.setDefaultProperties(Collections.singletonMap("server.port",portNumber));

        app.run(args);
    }

}
