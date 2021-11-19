package com.work.accounting.config;

import com.work.accounting.models.enums.AuthorityEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.work.accounting.security.filters.AuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/department/**").hasAnyAuthority(AuthorityEnum.DEPARTMENT_DIRECTOR.name(), AuthorityEnum.ADMIN.name())
                .antMatchers("/employee/register/**", "/employee/authentication/**").permitAll()
                .anyRequest().authenticated();
    }
}
