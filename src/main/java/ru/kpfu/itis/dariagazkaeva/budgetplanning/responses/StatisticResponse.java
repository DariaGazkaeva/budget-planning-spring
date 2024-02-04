package ru.kpfu.itis.dariagazkaeva.budgetplanning.responses;

import lombok.Builder;
import lombok.Data;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.MoneyOperation;

import java.util.List;

@Data
@Builder
public class StatisticResponse {
    private List<CategorySum> expense;
    private List<CategorySum> income;
}
