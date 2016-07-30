package com.crgreco.pgalerts.pokevision;

import com.crgreco.pgalerts.PGAlertsApplication;
import com.crgreco.pgalerts.PGAlertsConfiguration;
import com.crgreco.pgalerts.domain.Pokemon;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

public class PokevisionResourceIT {

    @ClassRule
    public static final DropwizardAppRule<PGAlertsConfiguration> RULE =
            new DropwizardAppRule<>(PGAlertsApplication.class, ResourceHelpers.resourceFilePath("conf.yml"));

    @Test
    public void shouldReturnListOfPokemon() {
        Client httpClient = new JerseyClientBuilder(RULE.getEnvironment()).build("testClient");

        Response response = httpClient.target(String.format("http://localhost:%d/pokevision", RULE.getLocalPort()))
                .queryParam("latitude", 51.497121994573)
                .queryParam("longitude", -0.12494802474976)
                .request()
                .get(Response.class);

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(new GenericType<List<Pokemon>>(){}), hasSize(greaterThan(0)));
    }
}
