package com.crgreco.pgalerts;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class PGAlertsApplication extends Application<PGAlertsConfiguration> {
    @Override
    public void run(PGAlertsConfiguration configuration, Environment environment) throws Exception {
    }

    public static void main(String[] args) throws Exception {
        new PGAlertsApplication().run(args);
    }
}
