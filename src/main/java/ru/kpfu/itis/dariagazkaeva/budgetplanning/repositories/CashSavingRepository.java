package ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.CashSaving;

import java.util.List;

@Repository
public interface CashSavingRepository extends CrudRepository<CashSaving, Long> {
    List<CashSaving> findAllByAuthorId(Long id);
}
