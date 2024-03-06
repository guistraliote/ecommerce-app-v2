package com.guistraliote.product;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @Inject
    ProductPublisher productPublisher;

    @POST
    public Response createProduct(ProductDTO productDTO) {
        productPublisher.sendPostProductToQueue(productDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductDTO productDTO) {
        if (!id.equals(productDTO.getId())) {
            throw new WebApplicationException("Product ID does not match the payload.", Response.Status.BAD_REQUEST);
        }
        productPublisher.sendPutProductToQueue(productDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        productPublisher.sendDeleteProductToQueue(id);
        return Response.noContent().build();
    }

    @GET
    public Response findAllPaged(@QueryParam("pageIndex") @DefaultValue("0") int pageIndex,
                                 @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        List<ProductDTO> products = productService.findAllPaged(pageIndex, pageSize);
        return Response.ok(products).build();
    }

    @GET
    @Path("/sku/{sku}")
    public Response findBySku(@PathParam("sku") String sku) {
        ProductDTO product = productService.findBySku(sku);
        return Response.ok(product).build();
    }

    @GET
    @Path("/title/{title}")
    public Response findByTitle(@PathParam("title") String title) {
        ProductDTO product = productService.findByTitle(title);
        return Response.ok(product).build();
    }
}
