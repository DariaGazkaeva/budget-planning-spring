package ru.kpfu.itis.dariagazkaeva.budgetplanning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.AddCategoryForm;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.Category;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.CategoryRepository;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.MoneyOperationRepository;

import java.util.List;

@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MoneyOperationService moneyOperationService;

    public List<Category> findAllByTypeAndAuthor(Boolean income, User author) {
        return categoryRepository.findAllByIncomeAndAuthor(income, author);
    }

    public Category save(AddCategoryForm addCategoryForm, User author) {
        Category category = Category.builder()
                .name(addCategoryForm.getName())
                .author(author)
                .income(addCategoryForm.getIncome())
                .build();
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    @Transactional
    public Category delete(Category category, User user) {
        try {
            if (category.getAuthor().equals(user)) {
                List<MoneyOperation> moneyOperations = moneyOperationService.findAllByCategory(category);
                String defaultCategoryName = category.getIncome() ? "Another income" : "Another expense";
                Category defaultCategory = findByName(defaultCategoryName);
                for (MoneyOperation operation : moneyOperations) {
                    operation.setCategory(defaultCategory);
                    moneyOperationService.save(operation, user);
                }
                categoryRepository.delete(category);
                return category;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
