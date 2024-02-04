package ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;

import java.util.List;

@Component
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public User findByEmail(String email) {
        List<User> results = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (results.isEmpty()) return null;
        else return results.get(0);
    }

    public User save(User user) {
        try {
            em.persist(user);
            em.flush();
            return findByEmail(user.getEmail());
        } catch (RuntimeException e) {
            return null;
        }
    }

    public User findById(Long id) {
        List<User> results = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getResultList();
        if (results.isEmpty()) return null;
        else return results.get(0);
    }
}
