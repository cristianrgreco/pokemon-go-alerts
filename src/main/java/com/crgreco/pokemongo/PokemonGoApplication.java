package com.crgreco.pokemongo;

import com.crgreco.pokemongo.pokevision.DefaultPokevision;
import com.crgreco.pokemongo.pokevision.PokevisionConfiguration;
import com.crgreco.pokemongo.pokevision.PokevisionHealthCheck;
import com.crgreco.pokemongo.pokevision.PokevisionResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;
import java.util.EnumSet;

import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_HEADERS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_METHODS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_ORIGINS_PARAM;

public class PokemonGoApplication extends Application<PokemonGoConfiguration> {

    @Override
    public void run(PokemonGoConfiguration configuration, Environment environment) throws Exception {
        Client httpClient = new JerseyClientBuilder(environment).build(getName());

        PokevisionConfiguration pokevisionConfiguration = configuration.getPokevisionConfiguration();
        PokevisionResource pokevisionResource = new PokevisionResource(new DefaultPokevision(httpClient, pokevisionConfiguration));
        PokevisionHealthCheck pokevisionHealthCheck = new PokevisionHealthCheck(httpClient, pokevisionConfiguration);
        environment.healthChecks().register("pokevision", pokevisionHealthCheck);
        environment.jersey().register(pokevisionResource);

        FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(ALLOWED_HEADERS_PARAM, "Content-Type,Accept,Origin");
        cors.setInitParameter(ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "*");
    }

    public static void main(String[] args) throws Exception {
        new PokemonGoApplication().run(args);
    }
}
