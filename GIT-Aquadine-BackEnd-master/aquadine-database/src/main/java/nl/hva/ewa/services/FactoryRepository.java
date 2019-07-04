package nl.hva.ewa.services;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FactoryRepository {

        //create entitymanager factory
    public EntityManagerFactory getNewFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aquadineDB");
        return emf;
    }
}
