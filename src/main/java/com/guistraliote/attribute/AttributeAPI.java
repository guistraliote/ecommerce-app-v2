package com.guistraliote.attribute;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/attribute")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttributeAPI {

    @Inject
    AttributeService attributeService;

    @GET
    @Path("/{id}")
    public Attribute getAttributeById(Long id) {
        return attributeService.getById(id);
    }
}
