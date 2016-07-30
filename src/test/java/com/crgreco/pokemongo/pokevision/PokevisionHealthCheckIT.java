package com.crgreco.pokemongo.pokevision;

import com.crgreco.pokemongo.PokemonGoApplication;
import com.crgreco.pokemongo.PokemonGoConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PokevisionHealthCheckIT {

    @ClassRule
    public static final DropwizardAppRule<PokemonGoConfiguration> RULE =
            new DropwizardAppRule<>(PokemonGoApplication.class, ResourceHelpers.resourceFilePath("conf.yml"));

    @Test
    public void shouldReturnHealthy() {
        assertThat(RULE.getEnvironment().healthChecks().runHealthCheck("pokevision").isHealthy(), is(true));
    }
}
