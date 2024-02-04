package ru.kpfu.itis.dariagazkaeva.budgetplanning.entities;

import javax.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "money_operation")
public class MoneyOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "authorid", nullable = false)
    private User author;
    @NotNull(message = "Sum must not be empty")
    private Float sum;
    @NotNull(message = "Date must not be empty")
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @Column(nullable = false)
    private Boolean income;
    @Length(max = 100)
    @Column(columnDefinition = "TEXT")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyOperation that = (MoneyOperation) o;
        return author.equals(that.author)
                && sum.equals(that.sum)
                && date.equals(that.date)
                && Objects.equals(category, that.category)
                && income.equals(that.income)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, sum, date, category, income, description);
    }
}

