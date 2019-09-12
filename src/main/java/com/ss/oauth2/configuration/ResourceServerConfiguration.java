package com.ss.oauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Autowired
  @Qualifier("tokenStoreBean")
  private TokenStore tokenStore;

  @Override
  public void configure(HttpSecurity http) throws Exception{
    http
        .requestMatchers()
        .antMatchers("/api/**")
      .and()
        .authorizeRequests()
          .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('api') and hasRole('ROLE_ADMIN')")
          .anyRequest().authenticated()
      .and()
        .exceptionHandling()
          .accessDeniedHandler(new OAuth2AccessDeniedHandler());
  }


  @Override
  public void configure(final ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(tokenStore);
  }

}
