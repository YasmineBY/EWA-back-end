package nl.hva.ewa.services;



import nl.hva.ewa.models.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepository {

    private FactoryRepository fact = new FactoryRepository();

    //Get all existing products
    public List<Product> getAllProducts(){
        EntityManager em = fact.getNewFactory().createEntityManager();
        List<Product> productList = em.createQuery("SELECT productname FROM Product WHERE  productname != null ").getResultList();
        return productList;
    }

    //Get product from it's name
    public Product getProductFromId(String productname) {
        EntityManager em = fact.getNewFactory().createEntityManager();
        Product product = em.find(Product.class,productname);
        em.close();
        return product;
    }


    //add a new product
    public void addProduct(Product product) {

        EntityManager em = fact.getNewFactory().createEntityManager();

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        em.close();
    }
    //deletes product
    public void deleteProduct(Product product) {
        EntityManager em = fact.getNewFactory().createEntityManager();

        em.getTransaction().begin();
        em.remove(em.contains(product) ? product : em.merge(product));


        em.getTransaction().commit();
        em.close();

    }


}
