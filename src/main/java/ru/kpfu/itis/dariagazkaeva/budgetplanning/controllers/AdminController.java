package ru.kpfu.itis.dariagazkaeva.budgetplanning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.AddCategoryForm;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.services.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminPanel() {
        return "adminPanel";
    }

    @RequestMapping(value = "/add-common-category", method = RequestMethod.GET)
    public String addCommonCategoryGet(ModelMap map) {
        map.put("addCategoryForm", new AddCategoryForm());
        return "addCommonCategory";
    }

    @RequestMapping(value = "/add-common-category", method = RequestMethod.POST)
    public String addCommonCategoryPost(@Valid @ModelAttribute("addCategoryForm") AddCategoryForm addCategoryForm,
                                    BindingResult result,
                                    @SessionAttribute User user,
                                    ModelMap map) {
        if (!result.hasErrors()) {
            try {
                categoryService.save(addCategoryForm, null);
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#showCommonCategories").build();
            } catch (DbException e) {
                map.put("statusCode", 500);
                return "error";
            }
        }
        return "addCommonCategory";
    }

    @RequestMapping(value = "/common-categories", method = RequestMethod.GET)
    public String showCommonCategories(ModelMap map) {
        map.put("incomeCategories", categoryService.findAllByTypeAndAuthor(true, null));
        map.put("expenseCategories", categoryService.findAllByTypeAndAuthor(false, null));
        return "commonCategories";
    }
}
