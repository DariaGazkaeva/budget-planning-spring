package ru.kpfu.itis.dariagazkaeva.budgetplanning.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.AddCategoryForm;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.Category;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.responses.CategoryResponse;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.services.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@Tag(name = "Category")
public class RestCategoryController {

//    http://localhost:8080/v3/api-docs/
//    http://localhost:8080/swagger-ui/index.html#/rest-category-controller

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Operation(summary = "Add Category")
    public ResponseEntity<?> addCategory(@Valid @ModelAttribute("addCategoryForm") AddCategoryForm addCategoryForm,
                                         BindingResult result,
                                         @SessionAttribute User user) {
        if (!result.hasErrors()) {
            try {
                Category category = categoryService.save(addCategoryForm, user);
                CategoryResponse categoryResponse = CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .income(category.getIncome()).build();
                return ResponseEntity.ok(categoryResponse);
            } catch (DbException e) {
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Delete Category")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Category category,
                                            @SessionAttribute User user) {
        try {
            Category deletedCategory = categoryService.delete(category, user);
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .id(deletedCategory.getId())
                    .name(deletedCategory.getName())
                    .income(deletedCategory.getIncome()).build();
            return ResponseEntity.ok(categoryResponse);
        } catch (DbException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
