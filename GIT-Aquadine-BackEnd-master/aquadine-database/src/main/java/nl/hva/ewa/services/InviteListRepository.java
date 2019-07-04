package nl.hva.ewa.services;

import nl.hva.ewa.models.Invitelist;
import nl.hva.ewa.models.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class InviteListRepository {

    private FactoryRepository fact = new FactoryRepository();
    private UserRepository userRepo = new UserRepository();

    public List<Invitelist> getAllInvites(){
        EntityManager em = fact.getNewFactory().createEntityManager();
        List<Invitelist> inviteHolder = em.createQuery("SELECT listId FROM Invitelist WHERE  listId != null ").getResultList();
        return inviteHolder;
    }


    //retrieve invitelist from Id
    public Invitelist getInviteList(int listId) {
        EntityManager em = fact.getNewFactory().createEntityManager();
        Invitelist invitelist = em.find(Invitelist.class, listId);
        em.close();
        return invitelist;
    }

    public Set<Invitelist> getUserInivtelists(String email){
        EntityManager em = fact.getNewFactory().createEntityManager();
        User user = em.find(User.class,email);
        return user.getInvitelists();

    }

    //adds a new inviteList
    public void addInviteList(Invitelist inviteList) {

        EntityManager em = fact.getNewFactory().createEntityManager();

        em.getTransaction().begin();
        em.persist(inviteList);
        em.getTransaction().commit();

        em.close();
    }



}
