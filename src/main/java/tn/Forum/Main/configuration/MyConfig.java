package tn.Forum.Main.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MyConfig {

@Bean
public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
}
}