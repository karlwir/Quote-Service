package se.kawi.quoteservice.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import se.kawi.quoteservice.webapi.AuthorWebApi;
import se.kawi.quoteservice.webapi.QuoteWebApi;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(QuoteWebApi.class);
        register(AuthorWebApi.class);
    }
}