package pl.springinaction.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.springinaction.tacocloud.model.Ingredient;
import pl.springinaction.tacocloud.model.Ingredient.*;
import pl.springinaction.tacocloud.model.Taco;

import javax.validation.Validation;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", IngrType.WRAP),
                new Ingredient("COCO", "Kukurydziana", IngrType.WRAP),
                new Ingredient("GRBF", "mielona wołowina ", IngrType.PROTEIN),
                new Ingredient("CARN", "kawałki mięsa", IngrType.PROTEIN),
                new Ingredient("TMTO", "pomidory pokrojone w kostkę", IngrType.VEGIES),
                new Ingredient("LETC", "sałata", IngrType.VEGIES),
                new Ingredient("CHED", "cheddar", IngrType.CHEESE),
                new Ingredient("JACK", "Monterey Jack", IngrType.CHEESE),
                new Ingredient("SLSA", "pikantny sos pomidorowy", IngrType.SAUCE),
                new Ingredient("SRCR", "śmietana", IngrType.SAUCE)

        );
        IngrType[] types = Ingredient.IngrType.values();
        for (IngrType type: types)
        {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "home";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngrType type){
        return ingredients.stream().filter(x-> x.getType().equals(type)).collect(Collectors.toList());
    }
}
