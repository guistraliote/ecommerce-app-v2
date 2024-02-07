package com.guistraliote.attribute;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/attribute")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttributeAPI {

    @Inject
    AttributeService attributeService;

    @POST
    @Transactional
    public Response createAttribute(@Valid AttributeDTO dto) {
        AttributeDTO createdAttributeDTO = attributeService.create(dto);

        return Response.status(Response.Status.CREATED).entity(createdAttributeDTO).build();
    }

    @GET
    @Path("/name/{name}")
    @Transactional
    public Response getAttributeByName(@PathParam("name") AttributeDTO dto) {
        AttributeDTO attributeDTO = attributeService.getByName(dto);

        return Response.ok(attributeDTO).build();
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Response getAttributeById(@PathParam("id") Long id) {
        AttributeDTO attributeDTO = attributeService.getById(id);

        return Response.ok(attributeDTO).build();
    }

    @GET
    @Path("/paged")
    @Transactional
    public Response getAllPaged(@QueryParam("page") int page, @QueryParam("size") int size) {
        List<AttributeDTO> attributeDTO = attributeService.getAllPaged(page, size);

        return Response.ok(attributeDTO).build();
    }

    @GET
    @Path("/active")
    @Transactional
    public Response getAllActive() {
        AttributeDTO attributeDTO = attributeService.getAllActive();

        return Response.ok(attributeDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAttribute(@PathParam("id") Long id, @Valid AttributeDTO dto) {
        AttributeDTO updatedAttribute = attributeService.update(id, dto);

        return Response.ok(updatedAttribute).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAttribute(@PathParam("id") Long id) {
        attributeService.delete(id);

        return Response.noContent().build();
    }
}
