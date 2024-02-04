package ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.responses.CategorySum;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.MoneyOperation;

import java.util.List;

@Component
public interface StatisticRepository extends CrudRepository<MoneyOperation, Long> {
    @Query("SELECT new ru.kpfu.itis.dariagazkaeva.budgetplanning.responses.CategorySum(m.category.name, SUM(m.sum)) FROM MoneyOperation m where m.author.id = :userId AND m.income = :income GROUP BY m.category.name")
    List<CategorySum> getStatistic(Boolean income, Long userId);

    @Query("SELECT m FROM MoneyOperation m WHERE m.author.id = :userId AND m.income = :income GROUP BY m.category, m.id HAVING m.sum >= (SELECT AVG(m.sum) FROM MoneyOperation m WHERE m.income = :income AND m.author.id = :userId)")
    List<MoneyOperation> getBiggestMoneyOperation(Boolean income, Long userId);
}
