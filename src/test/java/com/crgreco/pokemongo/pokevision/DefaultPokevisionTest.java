package com.crgreco.pokemongo.pokevision;

import com.crgreco.pokemongo.domain.Pokemon;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultPokevisionTest {

    private final Client httpClientMock = mock(Client.class);
    private final WebTarget webTargetMock = mock(WebTarget.class);
    private final Invocation.Builder builderMock = mock(Invocation.Builder.class);
    private final PokevisionConfiguration configurationMock = mock(PokevisionConfiguration.class);

    private final DefaultPokevision pokevision = new DefaultPokevision(httpClientMock, configurationMock);

    @Before
    public void setup() throws Exception {
        when(httpClientMock.target(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.request()).thenReturn(builderMock);

        when(configurationMock.getUri()).thenReturn("https://pokevision.com/map/data/%f/%f");
        when(configurationMock.getPokedex()).thenReturn(ImmutableMap.of(
                "001", "Bulbasaur",
                "002", "Ivysaur",
                "003", "Venusaur"
        ));
    }

    @Test
    public void shouldReturnListOfPokemon() {
        when(builderMock.get(PokevisionResponse.class)).thenReturn(new PokevisionResponse("success", asList(
                new Pokemon(1L, null, "001", null, 1.1, -1.1, 1000001),
                new Pokemon(2L, null, "002", null, 1.2, -1.2, 1000002),
                new Pokemon(3L, null, "003", null, 1.3, -1.3, 1000003)
        )));

        assertThat(pokevision.fetchPokemon(1.0, -1.0), is(asList(
                new Pokemon(1L, "001:1.100000:-1.100000", "001", "Bulbasaur", 1.1, -1.1, 1000001),
                new Pokemon(2L, "002:1.200000:-1.200000", "002", "Ivysaur", 1.2, -1.2, 1000002),
                new Pokemon(3L, "003:1.300000:-1.300000", "003", "Venusaur", 1.3, -1.3, 1000003)
        )));
    }
}
