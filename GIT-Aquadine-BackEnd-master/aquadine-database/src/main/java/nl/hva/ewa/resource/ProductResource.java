package nl.hva.ewa.resource;


import nl.hva.ewa.models.Product;
import nl.hva.ewa.models.rest.ClientError;
import nl.hva.ewa.services.ProductRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource { //http://localhost:8080/AquadineAPI-1.0/rest/product

    private ProductRepository prodRepo = new ProductRepository();

    @DELETE
    @Path("/delete/{productname}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productname") String productname) {
        try {
            Product product = prodRepo.getProductFromId(productname);
            prodRepo.deleteProduct(product);

            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            System.out.println("Unable to delete product: "+ productname);
        }
        return Response.status(Response.Status.OK).build();


    }


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnProductList() {
        ProductRepository prodRepo = new ProductRepository();

        if (prodRepo.getAllProducts().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(prodRepo.getAllProducts()).build();
        }

    }


    @GET
    @Path("/get/{productname}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductFromId(@PathParam("productname") String productname) {
        Product prod = prodRepo.getProductFromId(productname);
        if (prod == null) {
            return Response.status(Response.Status.NOT_FOUND).entity
                    (new ClientError("Product with id " + productname + " was not found")).build();
        }
        return Response.status(Response.Status.OK).entity(prod).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        try {
            prodRepo.addProduct(product);
            return Response.status(Response.Status.CREATED).entity(product).build();
        } catch (Exception e) {
            System.out.println("Unable to add product: " + product.getProductname());
                }
        return  Response.status(Response.Status.NO_CONTENT).build();



    }
}



