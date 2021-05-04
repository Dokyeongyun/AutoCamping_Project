package ROOT.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurationSupport {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }
}
