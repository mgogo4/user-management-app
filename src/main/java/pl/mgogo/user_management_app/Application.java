package pl.mgogo.user_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application main class.
 *
 * @author Michał Gogolewski
 */
@SpringBootApplication
public class Application {

    protected Application() {
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}