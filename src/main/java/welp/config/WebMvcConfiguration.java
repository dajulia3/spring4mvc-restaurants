package welp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ControllerAdvice
public class WebMvcConfiguration {

    @Bean(name ="pathHelper")
    public PathHelper PathRenderingHelper() {
        return new PathHelper();
    }

}