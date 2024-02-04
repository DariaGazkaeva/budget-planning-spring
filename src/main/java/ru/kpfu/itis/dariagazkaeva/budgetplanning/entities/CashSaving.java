package ru.kpfu.itis.dariagazkaeva.budgetplanning.entities;

import javax.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cash_saving")
public class CashSaving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @NotNull(message = "Goal must not be empty")
    private String goal;
    @ManyToOne
    @JoinColumn(name = "authorid", nullable = false)
    private User author;
    @NotNull(message = "Sum must not be empty")
    private Float sum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashSaving that = (CashSaving) o;
        return goal.equals(that.goal) && author.equals(that.author) && sum.equals(that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goal, author, sum);
    }
}
