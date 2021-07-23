package rookies.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import rookies.demo.model.RoleName;
import rookies.demo.security.jwt.JwtAuthEntryPoint;
import rookies.demo.security.jwt.JwtAuthTokenFilter;
import rookies.demo.security.jwt.JwtUtils;
import rookies.demo.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final JwtAuthEntryPoint unauthorizedHandler;


    @Autowired
    public ApplicationSecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, JwtAuthEntryPoint unauthorizedHandler){
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.unauthorizedHandler = unauthorizedHandler;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .cors().and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .authorizeRequests()
                .antMatchers("api/v1/auth**").anonymous()
                .antMatchers("api/v1/ratings**").permitAll()
                .antMatchers(HttpMethod.GET, "api/v1/products**", "api/v1/category**").permitAll().and()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "apl/v1/customers", "apl/v1/customers/**").hasAnyAuthority(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.POST, "api/v1/products/**", "api/v1/category/**").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "api/v1/products/**", "api/v1/category/**").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "api/v1/products/**", "api/v1/category/**").hasRole(RoleName.ADMIN.name()).and()
            .formLogin().loginPage("/login").permitAll().and()
            .logout().permitAll().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .httpBasic().and()
            .csrf().disable();
        
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class).anonymous();
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

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // provide UserDetailsService and PasswordEncoder for Spring
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(this.passwordEncoder);
        authenticationManagerBuilder
            .inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles(RoleName.ADMIN.name());
        
        authenticationManagerBuilder
            .inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("user")).roles(RoleName.CUSTOMER.name());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
