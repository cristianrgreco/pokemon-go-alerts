package com.crgreco.pgalerts.pokevision;

import com.crgreco.pgalerts.domain.Pokemon;

import javax.ws.rs.client.Client;
import java.util.List;

public class DefaultPokevision implements Pokevision {

    private final Client httpClient;
    private final PokevisionConfiguration configuration;

    public DefaultPokevision(Client httpClient, PokevisionConfiguration configuration) {
        this.httpClient = httpClient;
        this.configuration = configuration;
    }

    @Override
    public List<Pokemon> fetchPokemon(double latitude, double longitude) {
        return httpClient.target(String.format(configuration.getUri(), latitude, longitude))
                .request().get(PokevisionResponse.class).getPokemon();
    }
}
