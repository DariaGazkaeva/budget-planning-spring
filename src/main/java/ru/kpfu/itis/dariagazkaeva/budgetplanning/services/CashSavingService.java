package ru.kpfu.itis.dariagazkaeva.budgetplanning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.CashSaving;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.CashSavingRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CashSavingService {

    @Autowired
    private CashSavingRepository cashSavingRepository;
    public List<CashSaving> findAllByAuthorId(Long id) {
        return cashSavingRepository.findAllByAuthorId(id);
    }

    public CashSaving findByIdAndAuthorId(Long id, User author) {
        Optional<CashSaving> cashSaving = cashSavingRepository.findById(id);
        if (cashSaving.isPresent() && Objects.equals(cashSaving.get().getAuthor(), author)) {
            return cashSaving.get();
        }
        return null;
    }

    @Transactional
    public boolean edit(Long id, CashSaving cashSaving, User user) {
        cashSaving.setId(id);
        Optional<CashSaving> oldCashSaving = cashSavingRepository.findById(id);
        if (oldCashSaving.isPresent() && Objects.equals(oldCashSaving.get().getAuthor(), user)) {
            cashSaving.setAuthor(user);
            try {
                cashSavingRepository.save(cashSaving);
                return true;
            } catch (Exception e) {
                throw new DbException(e);
            }
        }
        return false;
    }

    @Transactional
    public boolean delete(Long id, User user) {
        Optional<CashSaving> cashSavingDB = cashSavingRepository.findById(id);
        if (cashSavingDB.isPresent() && Objects.equals(cashSavingDB.get().getAuthor(), user)) {
            cashSavingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public void save(CashSaving cashSaving) {
        try {
            cashSavingRepository.save(cashSaving);
        } catch (Exception e) {
            throw new DbException(e);
        }
    }
}
