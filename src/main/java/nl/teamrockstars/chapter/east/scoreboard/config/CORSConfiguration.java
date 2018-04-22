package nl.teamrockstars.chapter.east.scoreboard.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class CORSConfiguration extends WebSecurityConfigurerAdapter {

    private static Logger LOG = LoggerFactory.getLogger(CORSConfiguration.class);

    public CORSConfiguration() {
        LOG.info("Loading CORSConfiguration...");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
        http.csrf().disable();
    }
}