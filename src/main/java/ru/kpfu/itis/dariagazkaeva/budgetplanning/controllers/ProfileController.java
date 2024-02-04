package ru.kpfu.itis.dariagazkaeva.budgetplanning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.api.CurrencyApi;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.AddCategoryForm;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.AddMoneyOperationForm;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.PeriodDto;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.CashSaving;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.MoneyOperation;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.exceptions.DbException;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.responses.CategorySum;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.responses.StatisticResponse;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.services.CashSavingService;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.services.CategoryService;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.services.MoneyOperationService;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.services.StatisticService;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.utils.CurrencyRate;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.utils.GettingDay;

import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
@SessionAttributes("user")
public class ProfileController {

    @Autowired
    private MoneyOperationService moneyOperationService;
    @Autowired
    private CashSavingService cashSavingService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StatisticService statisticService;

    @RequestMapping("")
    public String getProfile(@SessionAttribute User user, ModelMap map) {
        Long userId = user.getId();
        GettingDay gettingDay = new GettingDay();
        Float monthIncome = moneyOperationService.getMonthlySum(user, gettingDay.getDayOfCurrentMonth(true), gettingDay.getDayOfCurrentMonth(false), true);
        Float monthExpense = moneyOperationService.getMonthlySum(user, gettingDay.getDayOfCurrentMonth(true), gettingDay.getDayOfCurrentMonth(false), false);
        List<CashSaving> cashSavings = cashSavingService.findAllByAuthorId(userId);

        map.put("username", user.getName());
        map.put("monthIncome", monthIncome);
        map.put("monthExpense", monthExpense);
        map.put("cashSavings", cashSavings);
        return "profile";
    }

//    --------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/add-cash-saving", method = RequestMethod.GET)
    public String addCashSavingGet(ModelMap map) {
        map.put("cashSaving", new CashSaving());
        return "editCashSaving";
    }

    @RequestMapping(value = "/add-cash-saving", method = RequestMethod.POST)
    public String addCashSavingPost(@Valid @ModelAttribute("cashSaving") CashSaving cashSaving,
                                    BindingResult result,
                                    @SessionAttribute User user,
                                    ModelMap map) {
        if (!result.hasErrors()) {
            cashSaving.setAuthor(user);
            try {
                cashSavingService.save(cashSaving);
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfile").build();
            } catch (DbException e) {
                map.put("statusCode", 500);
                return "error";
            }
        }
        return "editCashSaving";
    }

    @RequestMapping(value = "/edit-cash-saving/{cashSavingId}", method = RequestMethod.GET)
    public String editCashSavingGet(@PathVariable("cashSavingId") Long cashSavingId, @SessionAttribute User user, ModelMap map) {
        CashSaving cashSaving = cashSavingService.findByIdAndAuthorId(cashSavingId, user);
        if (cashSaving != null) {
            map.put("cashSaving", cashSaving);
            return "editCashSaving";
        } else {
            map.put("statusCode", 403);
            return "error";
        }
    }

    @RequestMapping(value = "/edit-cash-saving/{cashSavingId}", method = RequestMethod.POST)
    public String editCashSavingPost(@PathVariable("cashSavingId") Long cashSavingId,
                                     @Valid @ModelAttribute("cashSaving") CashSaving cashSaving,
                                     BindingResult result,
                                     @SessionAttribute User user, ModelMap map) {
        if (!result.hasErrors()) {
            try {
                if (!cashSavingService.edit(cashSavingId, cashSaving, user)) {
                    map.put("statusCode", 403);
                    return "error";
                }
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfile").build();
            } catch (DbException e) {
                map.put("statusCode", 500);
                return "error";
            }
        }
        return "editCashSaving";
    }

    @RequestMapping("/delete-cash-saving/{cashSavingId}")
    public String deleteCashSaving(@PathVariable("cashSavingId") Long cashSavingId, @SessionAttribute User user, ModelMap map) {
        if (!cashSavingService.delete(cashSavingId, user)) {
            map.put("statusCode", 403);
            return "error";
        }
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfile").build();
    }

//    --------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/add-money-operation", method = RequestMethod.GET)
    public String addMoneyOperationGet(@RequestParam Boolean income, @SessionAttribute User user, ModelMap map) {
        map.put("categories", categoryService.findAllByTypeAndAuthor(income, user));
        map.put("user", user);
        map.put("income", income);
        map.put("moneyOperation", new AddMoneyOperationForm());
        map.put("addCategoryForm", new AddCategoryForm());
//        map.put("deletableCategories", user.getOwnCategories().stream().filter(c -> c.getIncome().equals(income)).collect(Collectors.toList()));
        return "editMoneyOperation";
    }

    @RequestMapping(value = "/add-money-operation", method = RequestMethod.POST)
    public String addMoneyOperationPost(@RequestParam Boolean income,
                                        @Valid @ModelAttribute("moneyOperation") AddMoneyOperationForm addMoneyOperationForm,
                                        BindingResult result,
                                        @SessionAttribute User user,
                                        ModelMap map) {
        if (!result.hasErrors()) {
            try {
                MoneyOperation moneyOperation = MoneyOperation.builder()
                        .sum(addMoneyOperationForm.getSum())
                        .date(addMoneyOperationForm.getDate())
                        .category(addMoneyOperationForm.getCategory())
                        .income(addMoneyOperationForm.getIncome())
                        .description(addMoneyOperationForm.getDescription())
                        .build();
                moneyOperationService.save(moneyOperation, user);
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#showHistory").arg(0, income).build();
            } catch (DbException e) {
                map.put("statusCode", 500);
                return "error";
            }
        }
        map.put("categories", categoryService.findAllByTypeAndAuthor(income, user));
        map.put("user", user);
        map.put("addCategoryForm", new AddCategoryForm());
        return "editMoneyOperation";
    }

    @RequestMapping(value = "/edit-money-operation/{moneyOperationId}", method = RequestMethod.GET)
    public String editMoneyOperationGet(@PathVariable("moneyOperationId") Long moneyOperationId,
                                        @SessionAttribute User user,
                                        ModelMap map) {
        MoneyOperation moneyOperation = moneyOperationService.findByIdAndAuthorId(moneyOperationId, user);
        Boolean income = moneyOperation.getIncome();

        map.put("moneyOperation", moneyOperation);
        map.put("categories", categoryService.findAllByTypeAndAuthor(income, user));
        map.put("user", user);
        map.put("income", income);
        map.put("addCategoryForm", new AddCategoryForm());
        return "editMoneyOperation";
    }

    @RequestMapping(value = "/edit-money-operation/{moneyOperationId}", method = RequestMethod.POST)
    public String editMoneyOperationPost(@PathVariable("moneyOperationId") Long moneyOperationId,
                                         @Valid @ModelAttribute("moneyOperation") MoneyOperation moneyOperation,
                                         BindingResult result,
                                         @SessionAttribute User user,
                                         ModelMap map) {
        if (!result.hasErrors()) {
            MoneyOperation oldOperation = moneyOperationService.findByIdAndAuthorId(moneyOperationId, user);
            if (oldOperation != null) {
                moneyOperation.setIncome(oldOperation.getIncome());
                moneyOperation.setId(oldOperation.getId());
                moneyOperation.setAuthor(user);
                try {
                    moneyOperationService.edit(moneyOperation);
                    return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#showHistory").arg(0, oldOperation.getIncome()).build();
                } catch (DbException e) {
                    map.put("statusCode", 500);
                    return "error";
                }
            }
            map.put("statusCode", 403);
            return "error";
        }
        return "editMoneyOperation";
    }

    @RequestMapping(value = "/delete-money-operation/{moneyOperationId}", method = RequestMethod.GET)
    public String deleteMoneyOperation(@PathVariable("moneyOperationId") Long moneyOperationId,
                                       @SessionAttribute User user,
                                       ModelMap map) {
        Boolean income = moneyOperationService.findById(moneyOperationId).getIncome();
        if (!moneyOperationService.delete(moneyOperationId, user)) {
            map.put("statusCode", 403);
            return "error";
        }

        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#showHistory").arg(0, income).build();
    }

//    --------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showHistory(@RequestParam Boolean income, @SessionAttribute User user, ModelMap map) {
        GettingDay gettingDay = new GettingDay();
        map.put("moneyOperations", moneyOperationService
                .findAllByDatesAndType(income, user,
                        gettingDay.getDayOfCurrentMonth(true),
                        gettingDay.getDayOfCurrentMonth(false)));
        map.put("periodDto", new PeriodDto());
        return "history";
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public String showHistoryAnotherPeriod(@RequestParam Boolean income,
                                           @SessionAttribute User user,
                                           @ModelAttribute PeriodDto periodDto,
                                           ModelMap map) {
        map.put("moneyOperations",
                moneyOperationService.findAllByDatesAndType(income, user, periodDto.getBegin(), periodDto.getEnd()));
        map.put("periodDto", periodDto);
        return "history";
    }

//    --------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String showStatistics(ModelMap map) {
        return "statistics";
    }

    @RequestMapping(value = "/category-statistic", method = RequestMethod.GET)
    public String showCategoryStatistic(ModelMap map) {
        return "categoryStatistic";
    }

    @RequestMapping(value = "/operation-statistic", method = RequestMethod.GET)
    public String showOperationStatistic(ModelMap map, @SessionAttribute User user) {
        List<MoneyOperation> biggestIncomeOperations = statisticService.getBiggestMoneyOperation(true, user);
        List<MoneyOperation> biggestExpenseOperations = statisticService.getBiggestMoneyOperation(false, user);
        map.put("income", biggestIncomeOperations);
        map.put("expenses", biggestExpenseOperations);
        return "operationsStatistic";
    }

    @RequestMapping(value = "/statistics/list", method = RequestMethod.GET)
    @ResponseBody
    public StatisticResponse getStatistics(@SessionAttribute User user) {
        List<CategorySum> incomeStatistic = statisticService.getStatistic(true, user);
        List<CategorySum> expenseStatistic = statisticService.getStatistic(false, user);

        return StatisticResponse.builder()
                .expense(expenseStatistic)
                .income(incomeStatistic)
                .build();
    }

//    --------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/check-currency-rate", method = RequestMethod.GET)
    public String checkCurrency(ModelMap map) {
        map.put("currencyRate", new CurrencyRate());
        return "checkCurrency";
    }

    @RequestMapping(value = "/show-currency-rate", method = RequestMethod.GET)
    public String showCurrency(@Valid @ModelAttribute("currencyRate") CurrencyRate currencyRate,
                               BindingResult result, ModelMap map) {
        if (!result.hasErrors()) {
            List<String> currencies = new ArrayList<>();
            currencies.add(currencyRate.getCurrency1() + currencyRate.getCurrency2());
            try {
                Map<String, String> res = CurrencyApi.getRates(currencies);
                List<CurrencyRate> currencyRates = new ArrayList<>();
                res.forEach((k, v) -> {
                    String currency1 = k.substring(0, 3);
                    String currency2 = k.substring(3, 6);
                    String rate = v;
                    currencyRates.add(new CurrencyRate(currency1, currency2, rate));
                });
                if (!currencyRates.isEmpty()) {
                    map.put("rates", currencyRates);
                } else {
                    map.put("error", "Some error with parameters");
                }
            } catch (IOException e) {
                map.put("statusCode", 500);
                return "error";
            }
        }
        return "checkCurrency";
    }
}
