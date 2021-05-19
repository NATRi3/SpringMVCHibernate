package ru.javamentor.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.model.User;
import ru.javamentor.service.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @RequestMapping("/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "form";
        }
        userService.createOrUpdateUser(user);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "form";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}