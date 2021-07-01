package rookies.demo.datasources;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Configuration
public class PostgresDatasource {
    
    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource getDataSource(){
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}
