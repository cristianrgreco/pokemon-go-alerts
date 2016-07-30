package com.crgreco.pgalerts;

import com.crgreco.pgalerts.pokevision.DefaultPokevision;
import com.crgreco.pgalerts.pokevision.PokevisionConfiguration;
import com.crgreco.pgalerts.pokevision.PokevisionHealthCheck;
import com.crgreco.pgalerts.pokevision.PokevisionResource;
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

public class PGAlertsApplication extends Application<PGAlertsConfiguration> {

    @Override
    public void run(PGAlertsConfiguration configuration, Environment environment) throws Exception {
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
        new PGAlertsApplication().run(args);
    }
}
