package com.crgreco.pgalerts.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PokemonTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    
    @Test
    public void deserializesFromJSON() throws Exception {
        Pokemon pokemon = new Pokemon(704445888, "019", 51.517358197247, -0.13054726010754, 1469823019);
        assertThat(MAPPER.readValue(fixture("fixtures/pokemon.json"), Pokemon.class), is(pokemon));
    }
}
