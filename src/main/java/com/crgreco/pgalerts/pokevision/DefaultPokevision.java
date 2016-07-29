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
    public List<Pokemon> fetchPokemon() {
        return httpClient.target("https://pokevision.com/map/data/51.5202093/-0.1225081")
                .request().get(PokevisionResponse.class).getPokemon();
    }
}
