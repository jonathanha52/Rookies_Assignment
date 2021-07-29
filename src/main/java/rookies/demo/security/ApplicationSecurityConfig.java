package rookies.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
            .cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/ratings/**").permitAll()
                .antMatchers("/api/v1/**/public/**").permitAll()
                .antMatchers("/api/v1/**/admin/**").hasAnyAuthority(RoleName.ADMIN.name())
                .anyRequest().authenticated().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            
        http.logout().logoutUrl("api/v1/auth/signout").invalidateHttpSession(true);
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
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
