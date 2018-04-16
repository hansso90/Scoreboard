package nl.teamrockstars.chapter.east.scoreboard.config;

import java.util.stream.Stream;
import javax.sql.DataSource;
import nl.teamrockstars.chapter.east.scoreboard.model.Right;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * @author hans
 */
@Configuration
@EnableAuthorizationServer
public class OauthAuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

  private static Logger LOG = LoggerFactory.getLogger(WebSecurityConfiguration.class);

  private static final String[] OAUTH_SCOPES = new String[]{"read", "write"};

  private static final String[] OAUTH_GRANT_TYPES = new String[]{"password", "refresh_token"};

  public static final String RESOURCE_ID = "scoreboard_resources";

  private TokenStore tokenStore;

  @Autowired
  private DataSource dataSource;

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public OauthAuthorizationConfiguration() {
    LOG.info("Loading OauthAuthorizationConfiguration...");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .tokenStore(tokenStore())
        .authenticationManager(authenticationManager)
        .userDetailsService(userService)
        .approvalStoreDisabled();
  }


  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .jdbc(dataSource)
        .withClient("scoreboard")
        .secret("123456")
        .authorities(Stream.of(Right.values()).map(Right::getAuthority).toArray(String[]::new))
        .authorizedGrantTypes(OAUTH_GRANT_TYPES)
        .scopes(OAUTH_SCOPES);
  }


  @Bean
  public JdbcClientDetailsService clientDetailsService() {
    return new JdbcClientDetailsService(dataSource);
  }

  @Bean
  public ApprovalStore approvalStore() {
    return new JdbcApprovalStore(dataSource);
  }

  @Bean
  public AuthorizationCodeServices authorizationCodeServices() {
    return new JdbcAuthorizationCodeServices(dataSource);
  }

  @Bean
  public TokenStore tokenStore() {
    if (tokenStore == null) {
      return new JdbcTokenStore(dataSource);
    }
    return tokenStore;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    // clients can authenicatie with a form post with rest.
    oauthServer.allowFormAuthenticationForClients();
    oauthServer.tokenKeyAccess("isAnonymous()").checkTokenAccess("permitAll()");
  }
}
