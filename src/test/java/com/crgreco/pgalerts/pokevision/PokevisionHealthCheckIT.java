package com.crgreco.pgalerts.pokevision;

import com.crgreco.pgalerts.PGAlertsApplication;
import com.crgreco.pgalerts.PGAlertsConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PokevisionHealthCheckIT {

    @ClassRule
    public static final DropwizardAppRule<PGAlertsConfiguration> RULE =
            new DropwizardAppRule<>(PGAlertsApplication.class, ResourceHelpers.resourceFilePath("conf.yml"));

    @Test
    public void shouldReturnHealthy() {
        assertThat(RULE.getEnvironment().healthChecks().runHealthCheck("pokevision").isHealthy(), is(true));
    }
}
