package nl.hva.ewa.resource;



import nl.hva.ewa.models.Invitelist;
import nl.hva.ewa.services.InviteListRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/invited")
@Produces(MediaType.APPLICATION_JSON)
public class InviteListResource { //http://localhost:8080/AquadineAPI-1.0/rest/invited
    InviteListRepository inviteRepo = new InviteListRepository();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnInviteLists() {

        InviteListRepository inviteRepo = new InviteListRepository();

        if (inviteRepo.getAllInvites().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(inviteRepo.getAllInvites()).build();
        }

    }

    @GET
    @Path("/inviteTo/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userGetInviteLists(@PathParam("email") String email) {


        if (inviteRepo.getUserInivtelists(email) == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(inviteRepo.getUserInivtelists(email)).build();
        }

    }


    @POST
    @Path("/addinvitelist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInvitelist(Invitelist invitelist) {

        try {
            inviteRepo.addInviteList(invitelist);
            return Response.status(Response.Status.CREATED).entity(invitelist).build();
        } catch (Exception e) {
            System.out.println("InviteList was not created");
        }
        return Response.status(Response.Status.NO_CONTENT).build();

    }

        @GET
        @Path("/userlists/{listId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response userInviteLists ( @PathParam("listId") int listId){


            if (inviteRepo.getInviteList(listId) == null) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(Response.Status.OK).entity(inviteRepo.getInviteList(listId)).build();
            }

        }


    }
