package com.crgreco.pokemongo.pokevision;

import com.crgreco.pokemongo.domain.Pokemon;

import javax.ws.rs.client.Client;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
                .request().get(PokevisionResponse.class)
                .getPokemon()
                .stream().map(pokemon -> new Pokemon(
                        pokemon.getId(),
                        String.format("%s:%f:%f", pokemon.getPokemonId(), pokemon.getLatitude(), pokemon.getLongitude()),
                        pokemon.getPokemonId(),
                        configuration.getPokedex().get(pokemon.getPokemonId()),
                        pokemon.getLatitude(),
                        pokemon.getLongitude(),
                        pokemon.getExpirationTime()
                )).collect(toList());
    }
}
