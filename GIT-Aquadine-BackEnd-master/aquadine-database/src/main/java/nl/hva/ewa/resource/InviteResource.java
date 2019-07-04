package nl.hva.ewa.resource;


import nl.hva.ewa.models.Invite;
import nl.hva.ewa.models.Invitelist;
import nl.hva.ewa.services.InviteListRepository;
import nl.hva.ewa.services.InviteRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/inv")
@Produces(MediaType.APPLICATION_JSON)
public class InviteResource {


    InviteRepository inviteRepo = new InviteRepository();
    InviteListRepository inviteListRepository = new InviteListRepository();
    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnInviteLists(@PathParam("email") String email) {


        if(inviteRepo.getInvitesFromUser(email) == null){
            return  Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(inviteRepo.getInvitesFromUser(email)).build();
        }
    }

    @POST
    @Path("/{email}/{invitelist}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInviteList(@PathParam("email") String email, @PathParam("invitelist") int inviteList) {
        try{
            Invitelist templist=inviteListRepository.getInviteList(inviteList);
            inviteRepo.addInvitelist(email,templist);
            return Response.status(Response.Status.CREATED).entity(inviteList).build();
        } catch (Exception e) {
            System.out.println("unable to add " + email + "to inviteList");
        }
        return  Response.status(Response.Status.NO_CONTENT).build();

    }


    @DELETE
    @Path("/deleteinvite/{email}/{inviteListId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInvite(@PathParam("email") String email, @PathParam("inviteListId") int inviteList){
        try{
            Invite invite = inviteRepo.getInvite(email, inviteList);
            inviteRepo.deleteInvite(invite);
            return  Response.status(Response.Status.OK).build();

        } catch (Exception e) {
            System.out.println("unable to delete: " + email);
        }
        return  Response.status(Response.Status.NO_CONTENT).build();



    }


}
