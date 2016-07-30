package com.crgreco.pgalerts.pokevision;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pokevision")
@Produces(APPLICATION_JSON)
public class PokevisionResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokevisionResource.class);

    private final Pokevision pokevision;

    public PokevisionResource(Pokevision pokevision) {
        this.pokevision = pokevision;
    }

    @GET
    @Timed
    public Response fetchPokemon(
            @QueryParam("latitude") @NotNull Double latitude,
            @QueryParam("longitude") @NotNull Double longitude) {

        LOGGER.info("FetchPokemon(latitude: {}, longitude: {})", latitude, longitude);
        return Response.ok(pokevision.fetchPokemon(latitude, longitude)).build();
    }
}
