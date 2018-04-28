package nl.teamrockstars.chapter.east.scoreboard.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;

/**
 * @author hans
 */
@Component
public class PasswordFlowProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		String rawPassword = String.valueOf(usernamePasswordAuthenticationToken.getCredentials());

		User user = (User)userService.loadUserByUsername(userDetails.getUsername());
		if(user.getToken() != null && user.getTokenExpirationDate() != null && user.getTokenExpirationDate().isAfter(LocalDateTime.now())) {
			
			if(!user.getToken().equals(rawPassword)) {
				throw new BadCredentialsException("Invalid token");
			}
		} else {
			
			userService.checkPassword((User) userDetails, rawPassword);
		}

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		return userService.loadUserByUsername(username);
	}
}
