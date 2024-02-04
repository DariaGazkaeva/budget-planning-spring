package ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.Category;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;

import java.util.List;

@Component
public class MoneyOperationRepository {
    @PersistenceContext
    private EntityManager em;
    public Float getSumByDates(User user, String day1, String day2, boolean income) {
        List<MoneyOperation> result = em.createQuery("SELECT m FROM MoneyOperation m WHERE m.author = :author AND m.income = :income AND (m.date BETWEEN :day1 AND :day2)", MoneyOperation.class)
                .setParameter("author", user)
                .setParameter("income", income)
                .setParameter("day1", day1)
                .setParameter("day2", day2).getResultList();
        return result.stream().map(MoneyOperation::getSum).reduce(Float::sum).orElse((float) 0);
    }

    public List<MoneyOperation> findAllByDatesAndType(Boolean income, String day1, String day2, User user) {
        return em.createQuery("SELECT m FROM MoneyOperation m WHERE m.author = :user AND m.income = :income AND (m.date BETWEEN :day1 AND :day2)", MoneyOperation.class)
                .setParameter("user", user)
                .setParameter("income", income)
                .setParameter("day1", day1)
                .setParameter("day2", day2)
                .getResultList();
    }

    public MoneyOperation findByIdAndAuthorId(Long id, User user) {
        return em.createQuery("SELECT m FROM MoneyOperation m WHERE m.id = :id AND m.author = :author", MoneyOperation.class)
                .setParameter("author", user)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void update(MoneyOperation moneyOperation) {
        try {
            em.merge(moneyOperation);
        } catch (Exception e) {
            throw  new DbException(e);
        }
    }

    public MoneyOperation findById(Long id) {
        return em.createQuery("SELECT m FROM MoneyOperation m WHERE m.id = :id", MoneyOperation.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public boolean delete(Long id, User user) {
        int deleted = em.createQuery("DELETE FROM MoneyOperation m WHERE m.id = :id AND m.author = :user")
                .setParameter("id", id)
                .setParameter("user", user)
                .executeUpdate();
        return deleted > 0;
    }

    public void save(MoneyOperation moneyOperation) {
        try {
            em.persist(moneyOperation);
            em.flush();
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    public List<MoneyOperation> findAllByCategory(Category category) {
        return em.createQuery("SELECT m FROM MoneyOperation m WHERE m.category = :category", MoneyOperation.class)
                .setParameter("category", category).getResultList();
    }
}
