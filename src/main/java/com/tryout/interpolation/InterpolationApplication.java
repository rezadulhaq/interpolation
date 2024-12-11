package com.tryout.interpolation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class InterpolationApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterpolationApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Mengizinkan semua endpoint
                        .allowedOrigins("http://localhost:5173") // Mengizinkan origin tertentu
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metode HTTP yang diizinkan
                        .allowedHeaders("*") // Header yang diizinkan
                        .allowCredentials(true); // Jika menggunakan autentikasi berbasis cookie
            }
        };
    }
}
