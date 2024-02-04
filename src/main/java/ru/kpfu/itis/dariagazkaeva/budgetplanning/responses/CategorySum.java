package ru.kpfu.itis.dariagazkaeva.budgetplanning.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class CategorySum {
    private String label;
    private double value;
}

