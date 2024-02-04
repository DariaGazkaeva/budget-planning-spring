package ru.kpfu.itis.dariagazkaeva.budgetplanning.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.Category;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.CategoryRepository;

import java.util.Optional;

@Component
public class CategoryIdToCategoryConverter implements Converter<Long, Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category convert(Long source) throws IllegalArgumentException {
       Optional<Category> category = categoryRepository.findById(source);
       return category.orElseThrow(DbException::new);
    }
}
