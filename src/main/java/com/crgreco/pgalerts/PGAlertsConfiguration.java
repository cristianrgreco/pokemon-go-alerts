package com.crgreco.pgalerts;

import com.crgreco.pgalerts.pokevision.PokevisionConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PGAlertsConfiguration extends Configuration {

    @Valid
    @NotNull
    private PokevisionConfiguration pokevisionConfiguration;

    @JsonProperty("pokevision")
    public PokevisionConfiguration getPokevisionConfiguration() {
        return pokevisionConfiguration;
    }
}
