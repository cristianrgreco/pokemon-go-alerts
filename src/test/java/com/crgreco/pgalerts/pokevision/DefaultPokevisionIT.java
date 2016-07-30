package com.crgreco.pgalerts.pokevision;

import com.crgreco.pgalerts.PGAlertsApplication;
import com.crgreco.pgalerts.PGAlertsConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class DefaultPokevisionIT {

    @ClassRule
    public static final DropwizardAppRule<PGAlertsConfiguration> RULE =
            new DropwizardAppRule<>(PGAlertsApplication.class, ResourceHelpers.resourceFilePath("conf.yml"));

    @Test
    public void shouldReturnListOfPokemon() {
        Client httpClient = ClientBuilder.newClient();
        PokevisionConfiguration pokevisionConfiguration = RULE.getConfiguration().getPokevisionConfiguration();

        Pokevision pokevision = new DefaultPokevision(httpClient, pokevisionConfiguration);
        assertThat(pokevision.fetchPokemon(51.497121994573, -0.12494802474976), hasSize(greaterThan(0)));
    }
}
