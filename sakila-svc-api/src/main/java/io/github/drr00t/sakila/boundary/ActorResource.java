package io.github.drr00t.sakila.boundary;

import io.github.drr00t.sakila.control.CatalogManagement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/catalog/actors")
@ApplicationScoped
public class ActorResource {

    @Inject
    CatalogManagement catalogManagement;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActor(@Valid AddActor actor) {
        var result = catalogManagement.createActor(actor);

        if (!result.isValid()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(result.errorMessages())
                    .build();
        }
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors() {
        var result = catalogManagement.getAll();
        return Response.ok(result.entity()).build();
    }
}
