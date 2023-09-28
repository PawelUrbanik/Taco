package pl.springinaction.tacocloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.springinaction.tacocloud.model.Order;
import pl.springinaction.tacocloud.model.User;
import pl.springinaction.tacocloud.repository.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrdersController {

    private OrderRepository orderRepository;

    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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
        orderRepository.save(order);
        log.info("Zamówienie zostało złożone" + order.getTacos());
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(Model model, @AuthenticationPrincipal User user) {
        Pageable pageable = PageRequest.of(0, 20);
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
