package ru.kpfu.itis.dariagazkaeva.budgetplanning.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.LoginDto;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.RegistrationDto;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerGet(ModelMap map) {
        map.put("registrationDto", new RegistrationDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerPost(
            ModelMap map,
            @Valid @ModelAttribute("registrationDto") RegistrationDto registrationDto,
            BindingResult result, HttpSession session
    ) {
        if (!result.hasErrors()) {
            if (userService.checkEmailUniqueness(registrationDto.getEmail())) {
                User user = userService.register(registrationDto);
                if (user != null) {
                    user.eraseCredentials();
                    session.setAttribute("user", user);
                    return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#getProfile").build();
                } else {
                    map.put("errors", "Registration failed for unknown reason");
                }
            } else {
                map.put("errors", "This email is already registered");
            }
        }
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map, @Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult result, HttpSession session) {
        AuthenticationException exception = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (exception != null) {
            map.put("showErrors", true);
        }
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("UC#main").build();
    }
}
