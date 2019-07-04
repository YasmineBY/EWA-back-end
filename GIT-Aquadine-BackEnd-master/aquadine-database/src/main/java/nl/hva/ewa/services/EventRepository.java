package nl.hva.ewa.services;

import nl.hva.ewa.models.Event;
import nl.hva.ewa.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Set;

public class EventRepository {
    private FactoryRepository fact = new FactoryRepository();
    private InviteListRepository inviteListRepository = new InviteListRepository();
    private UserRepository userRepository = new UserRepository();

    //Retrieve an event from their inviteListId
    public Event getEventFromListId(int listId) {

        EntityManager em = fact.getNewFactory().createEntityManager();
        Query query = em.createQuery("SELECT m from Event m where invitelist.listId= '" + listId + "'");

        Event event = (Event) query.getSingleResult();
        em.close();
        return event;
    }

    //add a new event
    public void addEvent(Event event) {

        EntityManager em = fact.getNewFactory().createEntityManager();

        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();

        em.close();
    }
    //add a new event to a user
    public void addEventToUser(Event event, String email) {


        EntityManager em = fact.getNewFactory().createEntityManager();


        User user = em.find(User.class,email);

        user.addEvent(event);
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }
    //Get all events from a user
    public Set<Event> getAllEventFromUser(String email) {

        EntityManager em = fact.getNewFactory().createEntityManager();

        User user = userRepository.getUserFromEmail(email);

        Set<Event> events = user.getEvent();

        em.close();
        return events;


    }
    //Gets an event from their Id.
    public Event getEventFromParam(int eventId){
        EntityManager em = fact.getNewFactory().createEntityManager();
        try{
       Query query = em.createQuery("SELECT m from Event m where m.eventId= '" + eventId  + "'");
        Event event = (Event) query.getSingleResult();
            return event;

        } catch (Exception e) {
        return null;
        }

    }
}
