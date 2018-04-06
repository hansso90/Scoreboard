package nl.teamrockstars.chapter.east.scoreboard.controller.restricted;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.PRIVATE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = PRIVATE + "/example")
@Api(tags = "Restricted Example Controller", description = "Restricted Example Controller")
public class PrivateExampleController {

  @RequestMapping(value = "secure", method = RequestMethod.GET)
  @ApiOperation(value = "Check public controller is accessible", notes = "", response = String.class)
  public String getSecure()
  {
    return "secure";
  }
}
