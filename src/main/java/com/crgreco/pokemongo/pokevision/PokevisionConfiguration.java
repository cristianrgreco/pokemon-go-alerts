package com.crgreco.pokemongo.pokevision;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class PokevisionConfiguration {

    @Valid
    @NotEmpty
    private String uri;

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @Valid
    @NotNull
    private Map<String, String> pokedex;

    public Map<String, String> getPokedex() {
        return pokedex;
    }
}
