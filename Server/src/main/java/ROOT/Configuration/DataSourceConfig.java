package ROOT.Configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:chabak_db.properties")
public class DataSourceConfig {

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource(
            @Value("${database.driverClassName}") String driverClassName,
            @Value("${database.username}") String username,
            @Value("${database.password}") String password,
            @Value("${database.url}") String url,
            @Value("${cp.maxTotal}") int maxTotal,
            @Value("${cp.maxIdle}") int maxIdle,
            @Value("${cp.minIdle}") int minIdle,
            @Value("${cp.maxWaitMillis}") long maxWaitMillis){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxTotal(maxTotal);
        basicDataSource.setMaxIdle(maxIdle);
        basicDataSource.setMinIdle(minIdle);
        basicDataSource.setMaxWaitMillis(maxWaitMillis);

        return basicDataSource;
    }
}
