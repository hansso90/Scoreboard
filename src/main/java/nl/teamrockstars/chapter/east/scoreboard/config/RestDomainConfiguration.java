package nl.teamrockstars.chapter.east.scoreboard.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import nl.teamrockstars.chapter.east.scoreboard.dto.mapping.JacksonObjectMapper;

@Configuration
@Component
public class RestDomainConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        super.configureMessageConverters(converters);

        converters.add(new MappingJackson2HttpMessageConverter(JacksonObjectMapper.buildObjectMapper()));
    }

}

