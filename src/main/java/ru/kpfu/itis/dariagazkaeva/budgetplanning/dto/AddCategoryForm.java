package ru.kpfu.itis.dariagazkaeva.budgetplanning.dto;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AddCategoryForm {

    @NotNull
    private String name;
    @NotNull
    private Boolean income;
}
