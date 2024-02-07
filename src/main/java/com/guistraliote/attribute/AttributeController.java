package com.guistraliote.attribute;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/attributes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttributeController {

    @Inject
    AttributeService attributeService;

    @GET
    public Response getAllAttributes() {
        List<AttributeDTO> attributes = attributeService.findAll();
        return Response.ok(attributes).build();
    }

    @GET
    @Path("/{id}")
    public Response getAttributeById(@PathParam("id") Long id) {
        AttributeDTO attributeDTO = attributeService.findById(id);
        if (attributeDTO != null) {
            return Response.ok(attributeDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createAttribute(AttributeDTO attributeDTO) {
        AttributeDTO createdAttribute = attributeService.create(attributeDTO);
        return Response.status(Response.Status.CREATED).entity(createdAttribute).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAttribute(@PathParam("id") Long id, AttributeDTO attributeDTO) {
        AttributeDTO updatedAttribute = attributeService.update(id, attributeDTO);
        if (updatedAttribute != null) {
            return Response.ok(updatedAttribute).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAttribute(@PathParam("id") Long id) {
        attributeService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}