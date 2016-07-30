package com.crgreco.pgalerts.pokevision;

import com.crgreco.pgalerts.domain.Pokemon;

import java.util.List;

class PokevisionResponse {

    private String status;
    private List<Pokemon> pokemon;

    public PokevisionResponse() {
    }

    public PokevisionResponse(String status, List<Pokemon> pokemon) {
        this.status = status;
        this.pokemon = pokemon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
