package com.crgreco.pokemongo.pokevision;

import com.crgreco.pokemongo.domain.Pokemon;

import java.util.List;

public interface Pokevision {

    List<Pokemon> fetchPokemon(double latitude, double longitude);
}
