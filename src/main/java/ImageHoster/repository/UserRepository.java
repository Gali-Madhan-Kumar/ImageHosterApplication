package ImageHoster.repository;

import ImageHoster.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public void registerUser(User newUser) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newUser);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public User checkUser(String userName, String password) {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND  u.password = :password", User.class);
            typedQuery.setParameter("username", userName);
            typedQuery.setParameter("password", password);
            return typedQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}