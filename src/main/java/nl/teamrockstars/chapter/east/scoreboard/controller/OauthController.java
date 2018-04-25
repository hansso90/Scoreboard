package nl.teamrockstars.chapter.east.scoreboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import nl.teamrockstars.chapter.east.scoreboard.dto.ChapterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@Api(tags = "OAuth Controller", description = "Management oauth controller")
@PreAuthorize("isAuthenticated()")
public class OauthController {

  @Autowired
  private TokenStore tokenStore;

  @RequestMapping(method = RequestMethod.DELETE, value = "/logout")
  @ApiOperation(value = "revoke the token", notes = "revoke the current used token")
  public HttpStatus revokeToken(HttpServletRequest request) {
    String authorization = request.getHeader("Authorization");
    if (authorization != null && authorization.contains("Bearer")){
      String tokenId = authorization.substring("Bearer".length()+1);
      OAuth2AccessToken token = tokenStore.readAccessToken(tokenId);
      tokenStore.removeAccessToken(token);
    }
    return HttpStatus.OK;
  }

}
