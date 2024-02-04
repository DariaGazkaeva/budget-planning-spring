package ru.kpfu.itis.dariagazkaeva.budgetplanning.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class PeriodDto {

    @NotBlank
    private String begin;
    @NotBlank
    private String end;
}
