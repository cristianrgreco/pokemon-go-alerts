package com.crgreco.pgalerts;

import com.crgreco.pgalerts.pokevision.PokevisionHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class PGAlertsApplication extends Application<PGAlertsConfiguration> {

    @Override
    public void run(PGAlertsConfiguration configuration, Environment environment) throws Exception {
        Client httpClient = new JerseyClientBuilder(environment).build(getName());

        PokevisionHealthCheck pokevisionHealthCheck = new PokevisionHealthCheck(httpClient);
        environment.healthChecks().register("pokevision", pokevisionHealthCheck);
    }

    public static void main(String[] args) throws Exception {
        new PGAlertsApplication().run(args);
    }
}
