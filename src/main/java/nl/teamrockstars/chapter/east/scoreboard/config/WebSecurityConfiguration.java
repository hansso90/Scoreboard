package nl.teamrockstars.chapter.east.scoreboard.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.DefaultCorsProcessor;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static Logger LOG = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    public WebSecurityConfiguration() {
        LOG.info("Loading WebSecurityConfiguration...");
    }

    @Autowired
    private PasswordFlowProvider passwordFlowProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(passwordFlowProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @return BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CorsFilter corsFilter() {
        
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
       config.addAllowedMethod(HttpMethod.POST);
       config.addAllowedMethod(HttpMethod.GET);
       config.addAllowedMethod(HttpMethod.PUT);
       config.addAllowedMethod(HttpMethod.DELETE);
       config.addAllowedHeader( "Authorization" );
       source.registerCorsConfiguration("/**", config);
       FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
       bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
       
       CorsFilter filter = new CorsFilter(source);
       filter.setCorsProcessor( new DefaultCorsProcessor() );
       
       return filter;
    }

}
