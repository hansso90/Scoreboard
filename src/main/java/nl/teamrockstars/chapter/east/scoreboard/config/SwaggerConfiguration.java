package nl.teamrockstars.chapter.east.scoreboard.config;

import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.BASE;
import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.MOUNT;
import static nl.teamrockstars.chapter.east.scoreboard.controller.RouteConstants.VERSION;

import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private static Logger LOG = LoggerFactory.getLogger(SwaggerConfiguration.class);

  public SwaggerConfiguration() {
    LOG.info("Loading SwaggerConfiguration...");
  }


  @Bean
  public Docket scoreboardApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .paths(Predicates.or(
              PathSelectors.regex("/api/v0/.*"),
              PathSelectors.regex("/oauth/token"),
              PathSelectors.regex("/oauth/check_token"),
              PathSelectors.regex("/oauth/logout")
            )
        )
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Team Rockstars IT Scoreboard")
        .description("Team Rockstars IT Scoreboard")
        .termsOfServiceUrl("TODO")
        .contact(new Contact("Team Rockstars IT", "https://www.teamrockstars.nl",
            "beheer@teamrockstars.nl"))
        .license("Apache License Version 2.0")
        .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
        .version(String.valueOf(VERSION))
        .build();
  }


  @Controller
  class SwaggerRedirections {

    @RequestMapping({BASE, MOUNT})
    public String home() {
      return "redirect:/swagger-ui.html";
    }
  }

}
