package rookies.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import rookies.demo.model.RoleName;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .cors().and()
            .authorizeRequests()
                .antMatchers("api/v1/").permitAll()
                .antMatchers("api/v1/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "api/v1/products/**", "api/v1/category/**").permitAll()
                .antMatchers(HttpMethod.POST, "api/v1/products/**", "api/v1/category/**").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "api/v1/products/**", "api/v1/category/**").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "api/v1/products/**", "api/v1/category/**").hasRole(RoleName.ADMIN.name()).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .httpBasic().and()
            .csrf().disable();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails testUser = User.builder().username("user")
            .password(passwordEncoder.encode("password"))
            .roles(RoleName.CUSTOMER.name())
            .build();
        
        UserDetails testAdmin = User.builder().username("admin")
            .password(passwordEncoder.encode("admin"))
            .roles(RoleName.ADMIN.name())
            .build();

        return new InMemoryUserDetailsManager(testUser, testAdmin);
    }
}
