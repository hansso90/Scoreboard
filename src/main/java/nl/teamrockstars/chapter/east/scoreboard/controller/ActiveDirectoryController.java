package nl.teamrockstars.chapter.east.scoreboard.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/ad")
public class ActiveDirectoryController {

    private static Logger LOG = LoggerFactory.getLogger(ActiveDirectoryController.class);

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
	public void postAD(HttpServletRequest request, HttpServletResponse response) throws IOException, MalformedClaimException {

		String token = request.getParameterMap().get(AuthParameterNames.ID_TOKEN)[0];
        createJwtConsumer(token);

		try {
			JwtClaims jwtDecoded = this.jwtConsumer.processToClaims(token);
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

    private void createJwtConsumer(String token) {
        String kid = null;
        String issuer = null;

        JwtConsumer firstPassConsumer = new JwtConsumerBuilder().setSkipAllDefaultValidators().setSkipSignatureVerification().build();
        try {
            kid = firstPassConsumer.process(token).getJoseObjects().get(0).getKeyIdHeaderValue();
            JwtClaims firstpassClaims = firstPassConsumer.processToClaims(token);
            try {
                issuer = firstpassClaims.getIssuer();
            } catch (MalformedClaimException e) {
                LOG.error(e.getMessage());
            }
            LOG.debug("Found kid: {}, issuer: {}.", kid, issuer);
        } catch (InvalidJwtException e) {
            LOG.error(e.getMessage());
        }

        JwtConsumerBuilder jwtConsumer = new JwtConsumerBuilder().setExpectedAudience(clientId).setExpectedIssuer(issuer);
        PublicKey key = azureADKeyService.createADPublicKey(getCertificateString(kid));
        if (key != null) {
            jwtConsumer = jwtConsumer.setVerificationKey(key);
        } else {
            LOG.warn("Verification key was null, this is unsafe.");
            jwtConsumer.setSkipAllDefaultValidators();
            jwtConsumer.setSkipSignatureVerification();
        }
        this.jwtConsumer = jwtConsumer.build();
    }

    private String getCertificateString(String kid)  {
        String certificateString = null;
        try {
            JSONObject json = new JSONObject(IOUtils.toString(new URL("https://login.microsoftonline.com/common/discovery/keys"), Charset.forName("UTF-8")));
            JSONArray keys = (JSONArray) json.get("keys");
            for (Object key : keys) {
                JSONObject keyObject = (JSONObject) key;
                if (StringUtils.equals(kid, keyObject.get("kid").toString())) {
                    certificateString = keyObject.get("x5c").toString();
                    certificateString = StringUtils.remove(certificateString, "[\"");
                    certificateString = StringUtils.remove(certificateString, "\"]");
                    LOG.debug("Found certificate: {}", certificateString);
                    break;
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return certificateString;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public RedirectView getLoginUrl() throws UnsupportedEncodingException {
		return new RedirectView(getRedirectUrl(redirectURI));
	}

	private String getRedirectUrl(String currentUri) throws UnsupportedEncodingException {

		String redirectUrl = authority + this.tenant //
				+ "/oauth2/authorize?response_type=code%20id_token&scope=openid&response_mode=form_post&redirect_uri="//
				+ URLEncoder.encode(currentUri, "UTF-8") + "&client_id=" + clientId //
				+ "&resource=https%3a%2f%2fgraph.windows.net" + "&nonce=" + UUID.randomUUID();// // "&site_id=500879&claim=email";
		return redirectUrl;
	}

}
