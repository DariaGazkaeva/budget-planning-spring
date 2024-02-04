package ru.kpfu.itis.dariagazkaeva.budgetplanning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.StatisticRepository;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.responses.CategorySum;

import java.util.List;

@Component
public class StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;

    public List<CategorySum> getStatistic(Boolean income, User user) {
        return statisticRepository.getStatistic(income, user.getId());
    }

    public List<MoneyOperation> getBiggestMoneyOperation(Boolean income, User user) {
        return statisticRepository.getBiggestMoneyOperation(income, user.getId());
    }
}
