package com.example.portfolio;
<<<<<<< HEAD
=======

>>>>>>> 95825a61d6ac466ee7f2a40751350db0024a27f3
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // This maps to the `/api` endpoints in your backend
<<<<<<< HEAD
                .allowedOrigins("http://localhost:3000")  // The URL of your frontend
=======
                .allowedOrigins("http://localhost:3004")  // The URL of your frontend
>>>>>>> 95825a61d6ac466ee7f2a40751350db0024a27f3
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }
}