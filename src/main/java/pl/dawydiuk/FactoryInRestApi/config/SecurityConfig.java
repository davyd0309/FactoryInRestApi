package pl.dawydiuk.FactoryInRestApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.dawydiuk.FactoryInRestApi.service.JwtFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
    }

}
