package ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.Category;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query("select c from Category c where c.income = :income and (c.author = :author or c.author is null)")
    List<Category> findAllByIncomeAndAuthor(Boolean income, User author);

    Category findByName(String name);
}
