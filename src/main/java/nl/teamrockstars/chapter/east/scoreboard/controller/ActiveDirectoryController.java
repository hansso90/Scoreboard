package nl.teamrockstars.chapter.east.scoreboard.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nl.teamrockstars.chapter.east.scoreboard.dto.ActiveDirectoryRedirectDto;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.repository.UserRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.AzureADKeyService;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;

@RestController
@RequestMapping(value = "/ad")
public class ActiveDirectoryController {

	private final class AuthParameterNames {

		private static final String ID_TOKEN = "id_token";
		private static final String UPN = "upn";
		private static final String NAME = "name";
	}

	@Value("${azure.activedirectory.clientId}")
	private String clientId;

	@Value("${azure.activedirectory.tenant}")
	private String tenant;

	@Value("${azure.activedirectory.authority}")
	private String authority;

	@Value("${azure.activedirectory.issuer}")
	private String issuer;

	@Value("${azure.activedirectory.frontEndBaseUrl}")
	private String frontEndBaseUrl;

	private String redirectURI = "http://localhost:8080/ad";

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AzureADKeyService azureADKeyService;

	private JwtConsumer jwtConsumer;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void postAD(HttpServletRequest request, HttpServletResponse response) throws IOException {

		JwtConsumerBuilder b = new JwtConsumerBuilder().setExpectedAudience(clientId).setExpectedIssuer(issuer);
		PublicKey key = azureADKeyService.getADPublicKey();

		if (key != null) {
			b = b.setVerificationKey(key);
		} else {
			b.setSkipAllDefaultValidators();
			b.setSkipSignatureVerification();
		}

		jwtConsumer = b.build();

		String token = request.getParameterMap().get(AuthParameterNames.ID_TOKEN)[0];

		try {
			JwtClaims jwtDecoded = jwtConsumer.processToClaims(token);
			String username = jwtDecoded.getStringClaimValue(AuthParameterNames.UPN);
			String name = jwtDecoded.getStringClaimValue(AuthParameterNames.NAME);
			User user = userService.findOrCreate(username, name);
			user.setToken(UUID.randomUUID().toString());
			user.setTokenExpirationDate(LocalDateTime.now().plusMinutes(5));

			userRepository.save(user);

			response.sendRedirect(frontEndBaseUrl + "?username=" + user.getUsername() + "&token=" + user.getToken());
		} catch (InvalidJwtException | MalformedClaimException e) {
			e.printStackTrace();
			response.sendRedirect(frontEndBaseUrl + "?failed=true");
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<ActiveDirectoryRedirectDto> getLoginUrl() throws UnsupportedEncodingException {

		ActiveDirectoryRedirectDto dto = new ActiveDirectoryRedirectDto();
		dto.setUrl(getRedirectUrl(redirectURI));

		return new ResponseEntity<ActiveDirectoryRedirectDto>(dto, HttpStatus.OK);
	}

	private String getRedirectUrl(String currentUri) throws UnsupportedEncodingException {

		String redirectUrl = authority + this.tenant //
				+ "/oauth2/authorize?response_type=code%20id_token&scope=openid&response_mode=form_post&redirect_uri="//
				+ URLEncoder.encode(currentUri, "UTF-8") + "&client_id=" + clientId //
				+ "&resource=https%3a%2f%2fgraph.windows.net" + "&nonce=" + UUID.randomUUID();// +
																																											// "&site_id=500879&claim=email";
																																											// //

		return redirectUrl;
	}

}
