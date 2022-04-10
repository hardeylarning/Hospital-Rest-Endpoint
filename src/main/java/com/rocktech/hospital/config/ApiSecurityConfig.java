package com.rocktech.hospital.config;

import com.rocktech.hospital.repository.StaffRepository;
import com.rocktech.hospital.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${hospital.key}")
    private String requestHeader;

    @Autowired
    private StaffService staffService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiAuthKeyFilter filter = new ApiAuthKeyFilter(requestHeader);
        filter.setAuthenticationManager(new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String principal = (String)  authentication.getPrincipal();
                String uuid = staffService.uuidValue(principal);
                if (uuid == null || !uuid.equals(principal) ){
                    throw new BadCredentialsException("Invalid uuid");
                }

                authentication.setAuthenticated(true);
                return authentication;
            }
        });
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests().antMatchers( "/h2-console/**").permitAll().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }
}
