package com.ss.oauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  @Qualifier("userDetailsServiceBean")
  private UserDetailsService userDetailsService;

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/webjars/**", "/css/**", "/images/**", "/js/**", "/oauth2/token");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .and()
           // .antMatcher("/user/**")
      .authorizeRequests()
        .antMatchers("/","/login","/logout.do", "/anon", "/health").permitAll()
        .antMatchers("/**").authenticated()
      .and()
        .formLogin()
          .loginProcessingUrl("/login.do")
            .usernameParameter("username")
            .passwordParameter("password")
          .loginPage("/login")
      .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("simple")
      .password(passwordEncoder.encode("simple"))
      .roles("ADMIN");
    auth.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


  @Override
  public UserDetailsService userDetailsService() {
    return userDetailsService;
  }


}
