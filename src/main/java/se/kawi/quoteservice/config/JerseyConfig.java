package se.kawi.quoteservice.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import se.kawi.quoteservice.resource.AuthorResource;
import se.kawi.quoteservice.resource.CORSFilter;
import se.kawi.quoteservice.resource.QuoteResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(QuoteResource.class);
        register(AuthorResource.class);
        register(CORSFilter.class);
    }
}