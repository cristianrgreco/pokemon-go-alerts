package com.crgreco.pgalerts;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;

public class PGAlertsApplication extends Application<PGAlertsConfiguration> {

    @Override
    public void run(PGAlertsConfiguration configuration, Environment environment) throws Exception {
        Client client = new JerseyClientBuilder(environment).build(getName());
    }

    public static void main(String[] args) throws Exception {
        new PGAlertsApplication().run(args);
    }
}
