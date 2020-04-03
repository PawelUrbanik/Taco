package pl.springinaction.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import pl.springinaction.tacocloud.model.Order;
import pl.springinaction.tacocloud.model.User;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping("/current")
    public String orderForm(Model model)
    {
        //TODO przekazać obiekt Taco i go przypisać do order
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping("")
    public String proccesOrder(@Valid Order  order, Errors errors , @AuthenticationPrincipal User user)
    {
        if (errors.hasErrors())
        {
            errors.getAllErrors().forEach(e -> System.out.println(e.getDefaultMessage()));
            return "orderForm";
        }
        order.setUser(user);
        log.info("Zamówienie zostało złożone" + order.getTacos());
        return "redirect:/";
    }
}
