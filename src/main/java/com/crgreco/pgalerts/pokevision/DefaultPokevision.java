package com.crgreco.pgalerts.pokevision;

import com.crgreco.pgalerts.domain.Pokemon;

import javax.ws.rs.client.Client;
import java.util.List;

public class DefaultPokevision implements Pokevision {

    private final Client httpClient;

    public DefaultPokevision(Client httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public List<Pokemon> fetchPokemon(double latitude, double longitude) {
        return httpClient.target(String.format("https://pokevision.com/map/data/%f/%f", latitude, longitude))
                .request().get(PokevisionResponse.class).getPokemon();
    }
}
