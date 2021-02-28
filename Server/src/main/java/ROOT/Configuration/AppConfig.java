package ROOT.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    JdbcTemplate JdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
