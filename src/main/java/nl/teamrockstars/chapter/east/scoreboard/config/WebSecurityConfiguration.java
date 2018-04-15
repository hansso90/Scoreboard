package nl.teamrockstars.chapter.east.scoreboard.config;


import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PRIVATE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static Logger LOG = LoggerFactory.getLogger(WebSecurityConfiguration.class);

  public WebSecurityConfiguration() {
    LOG.info("Loading WebSecurityConfiguration...");
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.authorizeRequests()
        .antMatchers(PRIVATE+"/**" ).authenticated()
        .anyRequest().permitAll();
    // @formatter:on

    /* These are needed for H2 in-memory database */
    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
