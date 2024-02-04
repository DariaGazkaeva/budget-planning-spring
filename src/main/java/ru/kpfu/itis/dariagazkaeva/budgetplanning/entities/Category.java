package ru.kpfu.itis.dariagazkaeva.budgetplanning.entities;

import javax.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "authorid")
    private User author;
    @NotNull(message = "Must not be empty")
    private Boolean income;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name) && author.equals(category.author) && income.equals(category.income);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, income);
    }

    @Override
    public String toString() {
        return name;
    }
}
