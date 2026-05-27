package com.example.umc.global.config;

import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.h2.console", name = "enabled", havingValue = "true")
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<Servlet> h2ConsoleServlet(
            @Value("${spring.h2.console.path:/h2-console}") String path
    ) throws ReflectiveOperationException {
        Servlet h2ConsoleServlet = (Servlet) Class.forName("org.h2.server.web.JakartaWebServlet")
                .getDeclaredConstructor()
                .newInstance();
        String urlMapping = path.endsWith("/") ? path + "*" : path + "/*";
        return new ServletRegistrationBean<>(h2ConsoleServlet, urlMapping);
    }
}
