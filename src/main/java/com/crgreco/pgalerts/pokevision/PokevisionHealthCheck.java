package com.crgreco.pgalerts.pokevision;

import com.codahale.metrics.health.HealthCheck;
import com.crgreco.pgalerts.domain.Pokemon;

import javax.ws.rs.client.Client;
import java.util.List;

public class PokevisionHealthCheck extends HealthCheck {

    private final Client httpClient;

    public PokevisionHealthCheck(Client httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    protected Result check() throws Exception {
        String uri = String.format("https://pokevision.com/map/data/%f/%f", 51.497121994573, -0.12494802474976);
        List<Pokemon> pokemon = httpClient.target(uri).request().get(PokevisionResponse.class).getPokemon();

        if (pokemon.isEmpty()) {
            return Result.unhealthy("Pokevision API is currently in maintenance");
        } else {
            return Result.healthy();
        }
    }
}
