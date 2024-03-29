package com.guistraliote.attribute;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;

@Path("/attributes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttributeController {

    @Inject
    AttributeService attributeService;

    @Inject
    AttributeGateway attributeGateway;

    @GET
    @Path("/paged")
    public Response findAllPaged(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        List<AttributeDTO> attributes = attributeService.findAllPaged(pageIndex, pageSize);

        return Response.ok(attributes).build();
    }

    @GET
    @Path("/{id}")
    public Response getAttributeById(@PathParam("id") Long id) {
        AttributeDTO attributeDTO = attributeService.findById(id);
        if (Objects.nonNull(attributeDTO)) {

            return Response.ok(attributeDTO).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createAttribute(AttributeDTO attributeDTO) {
        attributeGateway.sendPostAttributeToQueue(attributeDTO);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAttribute(@PathParam("id") Long id, AttributeDTO attributeDTO) {
        attributeGateway.sendPutAttributeToQueue(attributeDTO);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAttribute(@PathParam("id") Long id) {
        attributeGateway.sendDeleteAttributeToQueue(id);

        return Response.noContent().build();
    }
}