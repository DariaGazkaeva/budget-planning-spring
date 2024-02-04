package ru.kpfu.itis.dariagazkaeva.budgetplanning.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.Category;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
public class AddMoneyOperationForm {

    @NotNull(message = "Sum must not be empty")
    private Float sum;

    @NotNull(message = "Date must not be empty")
    private String date;

    @NotNull(message = "Category must not be empty")
    private Category category;

    @NotNull(message = "Must not be empty")
    private Boolean income;

    @Length(max = 100, message = "Max length - 100")
    private String description;
}
