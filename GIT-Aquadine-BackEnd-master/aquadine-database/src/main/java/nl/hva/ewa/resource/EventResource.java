package nl.hva.ewa.resource;

import nl.hva.ewa.models.Event;
import nl.hva.ewa.models.Invitelist;
import nl.hva.ewa.models.rest.ClientError;
import nl.hva.ewa.services.EventRepository;
import nl.hva.ewa.services.InviteListRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;


@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    public EventRepository eventRepository = new EventRepository();
    public InviteListRepository inviteListRepository = new InviteListRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{listId}")
    public Response getEventFromListId(@PathParam("listId") int listId) {
        Event event = eventRepository.getEventFromListId(listId);
        if (event == null) {
            return Response.status(Response.Status.NOT_FOUND).entity
                    (new ClientError("Unable to find listId: " + listId)).build();
        }
        return Response.status(Response.Status.OK).entity(event).build();
    }

    @Path("/userevents/{email}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Event> getAllEventFromUser(@PathParam("email") String email) {
        try {
            return eventRepository.getAllEventFromUser(email);
        } catch (Exception e) {
            System.out.println("Could not retrieve users");
        }
        return null;
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(Event event) {

        Invitelist inviteList = new Invitelist();

        inviteListRepository.addInviteList(inviteList);
        event.setInvitelist(inviteList);
        eventRepository.addEvent(event);
        return Response.status(Response.Status.CREATED).entity(event).build();


    }

    //todo
    @POST
    @Path("/post/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Event addEvent(@PathParam("email") String email, Event event) {

        try{
            Invitelist inviteList = new Invitelist();
            inviteListRepository.addInviteList(inviteList);
            event.setInvitelist(inviteList);
            eventRepository.addEvent(event);
            eventRepository.addEventToUser(event, email);
            return event;
        } catch (Exception e) {
            System.out.println("Unable to add event");
        }
        return null;
    }





    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query")
    public Response getUsers(@QueryParam("eventId") int eventId) {

        try {
            return Response.status(Response.Status.CREATED).entity(eventRepository.getEventFromParam(eventId)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity
                    (new ClientError("Event with ID: " + eventId + " was not found")).build();
        }


    }
}

