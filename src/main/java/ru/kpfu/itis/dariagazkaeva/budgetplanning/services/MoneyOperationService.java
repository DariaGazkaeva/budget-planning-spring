package ru.kpfu.itis.dariagazkaeva.budgetplanning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.Category;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.MoneyOperationRepository;

import java.util.List;

@Component
public class MoneyOperationService {

    @Autowired
    private MoneyOperationRepository moneyOperationRepository;

    public Float getMonthlySum(User user, String day1, String day2, boolean income) {
        return moneyOperationRepository.getSumByDates(user, day1, day2, income);
    }

    public List<MoneyOperation> findAllByDatesAndType(Boolean income, User user, String day1, String day2) {
        return moneyOperationRepository.findAllByDatesAndType(income, day1, day2, user);
    }

    public MoneyOperation findByIdAndAuthorId(Long id, User user) {
        return moneyOperationRepository.findByIdAndAuthorId(id, user);
    }

    @Transactional
    public void edit(MoneyOperation moneyOperation) {
        moneyOperationRepository.update(moneyOperation);
    }

    public MoneyOperation findById(Long id) {
        return moneyOperationRepository.findById(id);
    }

    @Transactional
    public boolean delete(Long id, User user) {
        return moneyOperationRepository.delete(id, user);
    }

    @Transactional
    public void save(MoneyOperation moneyOperation, User user) {
        moneyOperation.setAuthor(user);
        moneyOperationRepository.save(moneyOperation);
    }

    public List<MoneyOperation> findAllByCategory(Category category) {
        return moneyOperationRepository.findAllByCategory(category);
    }
}
