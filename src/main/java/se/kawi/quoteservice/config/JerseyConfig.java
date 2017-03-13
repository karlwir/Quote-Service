package se.kawi.quoteservice.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import se.kawi.quoteservice.resource.AuthFilter;
import se.kawi.quoteservice.resource.AuthorResource;
import se.kawi.quoteservice.resource.CorsFilter;
import se.kawi.quoteservice.resource.QuoteResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(QuoteResource.class);
        register(AuthorResource.class);
        register(CorsFilter.class);
        register(AuthFilter.class);
    }
}