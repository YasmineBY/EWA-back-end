package nl.hva.ewa.resource;


import nl.hva.ewa.models.rest.ClientError;
import nl.hva.ewa.services.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.hva.ewa.models.User;


@Path("/users") //http://localhost:8080/AquadineAPI-1.0/rest/users
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    UserRepository userRepo = new UserRepository();

    @GET
    @Path("/error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegularError() {
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).
                entity(new ClientError("Dit is een test error me")).build();
    }


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnUserList() {

        UserRepository users = new UserRepository();

        if (users.getAllUsers().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(users.getAllUsers()).build();
        }

    }

    @GET
    @Path("/names")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnNameList() {

        UserRepository users = new UserRepository();

        if (users.getAllInfo().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(users.getAllInfo()).build();
        }

    }

    @GET
    @Path("/get/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserEmail(@PathParam("email") String email) {
        User user = userRepo.getUserFromEmail(email);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity
                    (new ClientError("User with email " + email + " was not found")).build();
        }
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adNewUser(User user) {

    try{

        userRepo.addUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    } catch (Exception e) {
        System.out.println("User could not be added");
    }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    //todo
    @POST
    @Path("/accept/{email}/{invitelist}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInviteList(@PathParam("email") String email, @PathParam("invitelist") int inviteList) {


        userRepo.addInviteList(email, inviteList);
        return Response.status(Response.Status.CREATED).entity(inviteList).build();

    }


    @PUT
    @Path("/change/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePass(@PathParam("email") String email, User user) {
    try{
        userRepo.changePassword(user);
        return Response.status(Response.Status.CREATED).entity(user).build();

    } catch (Exception error) {
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }

    }


}
