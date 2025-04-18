package com.utp.piuratours.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class CorsMappingConfig implements WebMvcConfigurer {

    @Value("${cors.hosts.permitidos}")
    private List<String> hosts;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(hosts.toArray(new String[0]))
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}