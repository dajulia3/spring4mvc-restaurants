package welp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration // Also turns on @EnableWebMvc :-)
@ComponentScan
public class Application {

    public static void main(String[] args) throws Exception {
        run(args);
    }

    public static ConfigurableApplicationContext run(String[] args) {
        return SpringApplication.run(Application.class, args);
    }

    public static ConfigurableApplicationContext run() {
        return run(new String[]{});
    }



}