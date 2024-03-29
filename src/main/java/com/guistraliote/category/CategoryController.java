package com.guistraliote.category;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @Inject
    CategoryPublisher categoryPublisher;

    @GET
    public Response findAllPaged(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        List<CategoryDTO> categories = categoryService.findAllPaged(pageIndex, pageSize);

        return Response.ok(categories).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        CategoryDTO category = categoryService.findById(id);

        return Response.ok(category).build();
    }

    @POST
    public Response createCategory(CategoryDTO dto) {
        categoryPublisher.sendPostCategoryToQueue(dto);

       return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateCategory(@QueryParam("id") Long id, CategoryDTO dto) {
        categoryPublisher.sendPutCategoryToQueue(dto);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        categoryPublisher.sendDeleteCategoryToQueue(id);

        return Response.noContent().build();
    }
}
