package com.crgreco.pokemongo;

import com.crgreco.pokemongo.pokevision.PokevisionConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PokemonGoConfiguration extends Configuration {

    @Valid
    @NotNull
    private PokevisionConfiguration pokevisionConfiguration;

    @JsonProperty("pokevision")
    public PokevisionConfiguration getPokevisionConfiguration() {
        return pokevisionConfiguration;
    }
}
