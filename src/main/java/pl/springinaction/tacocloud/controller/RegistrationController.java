package pl.springinaction.tacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.springinaction.tacocloud.model.User;
import pl.springinaction.tacocloud.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping()
    public String register(@Valid User user, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(e->System.err.println(e.getDefaultMessage()));
            return "register";
        }
        userService.addUSer(user);
        return "redirect:/";
    }
}
