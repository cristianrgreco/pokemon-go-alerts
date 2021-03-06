package com.crgreco.pokemongo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    private long id;
    private String uuid;
    private String pokemonId;
    private String name;
    private double latitude;
    private double longitude;
    private int expirationTime;

    public Pokemon() {
    }

    public Pokemon(long id, String uuid, String pokemonId, String name, double latitude, double longitude, int expirationTime) {
        this.id = id;
        this.uuid = uuid;
        this.pokemonId = pokemonId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.expirationTime = expirationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        if (id != pokemon.id) return false;
        if (Double.compare(pokemon.latitude, latitude) != 0) return false;
        if (Double.compare(pokemon.longitude, longitude) != 0) return false;
        if (expirationTime != pokemon.expirationTime) return false;
        if (uuid != null ? !uuid.equals(pokemon.uuid) : pokemon.uuid != null) return false;
        if (pokemonId != null ? !pokemonId.equals(pokemon.pokemonId) : pokemon.pokemonId != null) return false;
        return name != null ? name.equals(pokemon.name) : pokemon.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (pokemonId != null ? pokemonId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + expirationTime;
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(String pokemonId) {
        this.pokemonId = String.format("%03d", Integer.parseInt(pokemonId));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("expirationTime")
    public int getExpirationTime() {
        return expirationTime;
    }

    @JsonProperty("expiration_time")
    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }
}
