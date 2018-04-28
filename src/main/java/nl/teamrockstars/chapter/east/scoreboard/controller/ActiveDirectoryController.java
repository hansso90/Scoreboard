package nl.teamrockstars.chapter.east.scoreboard.controller;

<<<<<<< HEAD
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;
=======
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
>>>>>>> Allow token login
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

import nl.teamrockstars.chapter.east.scoreboard.dto.ActivityDirectoryRedirectDto;
import nl.teamrockstars.chapter.east.scoreboard.dto.OAuthTokenDto;
import nl.teamrockstars.chapter.east.scoreboard.mapper.UserMapper;
import nl.teamrockstars.chapter.east.scoreboard.model.User;
import nl.teamrockstars.chapter.east.scoreboard.repository.UserRepository;
import nl.teamrockstars.chapter.east.scoreboard.service.UserService;

@RestController
@RequestMapping(value = "/ad")
public class ActiveDirectoryController {

	private final class AuthParameterNames {

		private static final String ERROR = "error";
		private static final String ERROR_DESCRIPTION = "error_description";
		private static final String ERROR_URI = "error_uri";
		private static final String ID_TOKEN = "id_token";
		private static final String CODE = "code";
	}

	@Value("${azure.activedirectory.clientId}")
	private String clientId;

	@Value("${azure.activedirectory.clientSecret}")
	private String clientSecret;

	@Value("${azure.activedirectory.tenant}")
	private String tenant;

	@Value("${azure.activedirectory.authority}")
	private String authority;

	private String redirectURI = "http://localhost:8080/ad";

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper mapper;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<OAuthTokenDto> getUser(HttpServletRequest request) {

		OAuthTokenDto dto = new OAuthTokenDto();
		return new ResponseEntity<OAuthTokenDto>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void postAD(HttpServletRequest request, HttpServletResponse response) throws InvalidKeySpecException, JoseException, MalformedClaimException, InvalidJwtException, IOException {

		String pubKeySTRING = "-----BEGIN PUBLIC KEY-----\n" +
				"MIIDBTCCAe2gAwIBAgIQev76BWqjWZxChmKkGqoAfDANBgkqhkiG9w0BAQsFADAt\n" + 
				"MSswKQYDVQQDEyJhY2NvdW50cy5hY2Nlc3Njb250cm9sLndpbmRvd3MubmV0MB4X\n" + 
				"DTE4MDIxODAwMDAwMFoXDTIwMDIxOTAwMDAwMFowLTErMCkGA1UEAxMiYWNjb3Vu\n" + 
				"dHMuYWNjZXNzY29udHJvbC53aW5kb3dzLm5ldDCCASIwDQYJKoZIhvcNAQEBBQAD\n" + 
				"ggEPADCCAQoCggEBAMgmGiRfLh6Fdi99XI2VA3XKHStWNRLEy5Aw/gxFxchnh2kP\n" + 
				"dk/bejFOs2swcx7yUWqxujjCNRsLBcWfaKUlTnrkY7i9x9noZlMrijgJy/Lk+HH5\n" + 
				"HX24PQCDf+twjnHHxZ9G6/8VLM2e5ZBeZm+t7M3vhuumEHG3UwloLF6cUeuPdW+e\n" + 
				"xnOB1U1fHBIFOG8ns4SSIoq6zw5rdt0CSI6+l7b1DEjVvPLtJF+zyjlJ1Qp7NgBv\n" + 
				"AwdiPiRMU4l8IRVbuSVKoKYJoyJ4L3eXsjczoBSTJ6VjV2mygz96DC70MY3avccF\n" + 
				"rk7tCEC6ZlMRBfY1XPLyldT7tsR3EuzjecSa1M8CAwEAAaMhMB8wHQYDVR0OBBYE\n" + 
				"FIks1srixjpSLXeiR8zES5cTY6fBMA0GCSqGSIb3DQEBCwUAA4IBAQCKthfK4C31\n" +
				"DMuDyQZVS3F7+4Evld3hjiwqu2uGDK+qFZas/D/eDunxsFpiwqC01RIMFFN8yvmM\n" + 
				"jHphLHiBHWxcBTS+tm7AhmAvWMdxO5lzJLS+UWAyPF5ICROe8Mu9iNJiO5JlCo0W\n" + 
				"pui9RbB1C81Xhax1gWHK245ESL6k7YWvyMYWrGqr1NuQcNS0B/AIT1Nsj1WY7efM\n" + 
				"JQOmnMHkPUTWryVZlthijYyd7P2Gz6rY5a81DAFqhDNJl2pGIAE6HWtSzeUEh3jC\n" + 
				"sHEkoglKfm4VrGJEuXcALmfCMbdfTvtu4rlsaP2hQad+MG/KJFlenoTK34EMHeBP\n" + 
				"DCpqNDz8UVNk\n" + 
				"-----END PUBLIC KEY-----";
	
		
		String token = request.getParameterMap().get(AuthParameterNames.ID_TOKEN)[0];

		JwtConsumerBuilder b = new JwtConsumerBuilder();
		
		b = b.setExpectedAudience("da69c979-31ac-4560-b3d2-a04beba0e3e5")//
				.setExpectedIssuer("https://sts.windows.net/9e8cdb6a-eda5-4cca-8b83-b40f0074d999/")//
				.setExpectedSubject("hPTaTBD4gsO7plEknTMe82Ns-KzdSqPz10XV1Ng5M24");

		try {
			RsaKeyUtil rsaKeyUtil = new RsaKeyUtil();
			PublicKey publicKey = rsaKeyUtil.fromPemEncoded(pubKeySTRING);

			b = b.setVerificationKey(publicKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JwtConsumer jwtConsumer = b.build();

		// validate and decode the jwt
		jwtConsumer.setSkipVerificationKeyResolutionOnNone(true);
		
		JwtClaims jwtDecoded = jwtConsumer.processToClaims(token);
		String username = jwtDecoded.getStringClaimValue("upn");
		String name = jwtDecoded.getStringClaimValue("name"); 
		
		User user = userService.findOrCreate(username, name);
		user.setToken(UUID.randomUUID().toString());
		user.setTokenExpirationDate(LocalDateTime.now().plusMinutes(1));
		
		userRepository.save( user );
		
		response.sendRedirect( "http://localhost:1337/login?username=" + user.getUsername() + "&token=" + user.getToken() );
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<ActivityDirectoryRedirectDto> getLoginUrl() throws UnsupportedEncodingException {

		ActivityDirectoryRedirectDto dto = new ActivityDirectoryRedirectDto();
		dto.setUrl(getRedirectUrl(redirectURI));

		return new ResponseEntity<ActivityDirectoryRedirectDto>(dto, HttpStatus.OK);
	}

	private String getRedirectUrl(String currentUri) throws UnsupportedEncodingException {

		String redirectUrl = authority + this.tenant //
				+ "/oauth2/authorize?response_type=code%20id_token&scope=openid&response_mode=form_post&redirect_uri="//
				+ URLEncoder.encode(currentUri, "UTF-8") + "&client_id=" + clientId //
				+ "&resource=https%3a%2f%2fgraph.windows.net" + "&nonce=" + UUID.randomUUID() + "&site_id=500879&claim=email"; //
		// logger.info("redirect url: " + redirectUrl);
		return redirectUrl;
	}

	public static boolean containsAuthenticationData(HttpServletRequest httpRequest) {
		return httpRequest.getMethod().equalsIgnoreCase("POST") && (httpRequest.getParameterMap().containsKey(AuthParameterNames.ERROR) || httpRequest.getParameterMap().containsKey(AuthParameterNames.ID_TOKEN) || httpRequest.getParameterMap().containsKey(AuthParameterNames.CODE));
	}

}
