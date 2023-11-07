package com.venk.org.tutorials.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author venkateshkaradbhajne
 */

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(
    value = "authentication.basic.auth",
    havingValue = "true",
    matchIfMissing = false)
public class SpringAppSecurityAdapter
    extends WebSecurityConfigurerAdapter {

  @Autowired
  Environment environment;

  @Autowired
  private SpringAppAuthEntryPoint authenticationEntryPoint;

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user1 = User
        .withUsername(environment.getProperty("spring.security.user1.name"))
        .password(environment.getProperty("spring.security.user1.password"))
        .roles("USER")
        .build();
    UserDetails user2 = User
        .withUsername(environment.getProperty("spring.security.user2.name"))
        .password(environment.getProperty("spring.security.user2.password"))
        .roles("USER")
        .build();
    UserDetails admin = User
        .withUsername(environment.getProperty("spring.security.admin.name"))
        .password(environment.getProperty("spring.security.admin.password"))
        .roles("USER","ADMIN")
        .build();
    List<UserDetails> userDetails = new ArrayList<>();
    userDetails.add(user1);
    userDetails.add(user2);
    userDetails.add(admin);
    return new InMemoryUserDetailsManager(userDetails);
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable()
        .authorizeRequests().anyRequest().authenticated()
        .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);

    httpSecurity.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Argon2PasswordEncoder();
  }

}