package nl.hva.ewa.services;

import nl.hva.ewa.models.Invite;
import nl.hva.ewa.models.Invitelist;
import nl.hva.ewa.models.embaddable.InviteId;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class InviteRepository {

    private FactoryRepository fact = new FactoryRepository();
    private UserRepository userRepo = new UserRepository();
    private InviteListRepository inviteListRepository = new InviteListRepository();



    public List<Invitelist> getInvitesFromUser(String email) {
        EntityManager em = fact.getNewFactory().createEntityManager();

        Query query = em.createQuery("SELECT inviteId.invitelist FROM Invite m WHERE m.id.email =  '"+email+"'");

        List<Invitelist> invitelist = query.getResultList();
        em.close();
        return invitelist;

    }

    public void addInvitelist(String email, Invitelist invitelist) {

        EntityManager em = fact.getNewFactory().createEntityManager();
        InviteId adder = new InviteId(email,invitelist);
        Invite invite = new Invite(adder);

        em.getTransaction().begin();
        em.persist(invite);
        em.getTransaction().commit();
        em.close();
    }


    public Invite getInvite(String email, int inviteList){
        EntityManager em = fact.getNewFactory().createEntityManager();


        Query query = em.createQuery("SELECT m FROM Invite m WHERE m.id.email =  '"+email+"' and m.id.invitelist ='"+inviteList+"'");

        Invite invite = (Invite) query.getSingleResult();
        em.close();
        return invite;


    }




    public void deleteInvite(Invite invite) {
        EntityManager em = fact.getNewFactory().createEntityManager();


        em.getTransaction().begin();
        em.remove(em.contains(invite) ? invite : em.merge(invite));


        em.getTransaction().commit();
        em.close();

    }


}
