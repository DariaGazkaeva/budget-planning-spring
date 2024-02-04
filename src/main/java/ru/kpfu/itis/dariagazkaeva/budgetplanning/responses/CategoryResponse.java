package ru.kpfu.itis.dariagazkaeva.budgetplanning.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class CategoryResponse {
    private Long id;
    private String name;
    private Boolean income;
}
