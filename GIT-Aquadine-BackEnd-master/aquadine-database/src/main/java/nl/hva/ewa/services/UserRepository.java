package nl.hva.ewa.services;


import nl.hva.ewa.models.Invitelist;
import nl.hva.ewa.models.User;

import javax.persistence.EntityManager;


import java.util.List;


public class UserRepository {

    private FactoryRepository fact = new FactoryRepository();
    //get all users
    public List<User> getAllUsers(){
        EntityManager em = fact.getNewFactory().createEntityManager();
        List<User> userList = em.createQuery("SELECT email FROM User WHERE  email != null ").getResultList();
        return userList;
    }
    //retrieves a list of names from user
    public List<User> getAllInfo(){
        EntityManager em = fact.getNewFactory().createEntityManager();
        List<User> infoList = em.createQuery("SELECT name FROM User ").getResultList();
        return infoList;
    }
    //gets a single user based on their email
    public User getUserFromEmail(String email) {
        EntityManager em = fact.getNewFactory().createEntityManager();
        User user = em.find(User.class,email);
        em.close();
        return user;
    }

    //changes user password
        public void changePassword(User user){
            EntityManager em = fact.getNewFactory().createEntityManager();

            User tempUser = getUserFromEmail(user.getEmail());
            tempUser.setPassword(user.getPassword());


            em.getTransaction().begin();
            em.merge(tempUser);

            em.getTransaction().commit();

        }
    public void addInviteList(String email, int inviteList){
        EntityManager em = fact.getNewFactory().createEntityManager();


        Invitelist invitelist = em.find(Invitelist.class,inviteList);
        User user = em.find(User.class,email);

        user.addInviteList(invitelist);

        em.getTransaction().begin();
        em.merge(user);

        em.getTransaction().commit();

    }
    //adds a new user
    public void addUser(User user) {

        EntityManager em = fact.getNewFactory().createEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();
    }



}

