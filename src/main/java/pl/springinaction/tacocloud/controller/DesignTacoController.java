package pl.springinaction.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.springinaction.tacocloud.model.Ingredient;
import pl.springinaction.tacocloud.model.Ingredient.IngrType;
import pl.springinaction.tacocloud.model.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String home(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", IngrType.WRAP),
                new Ingredient("COCO", "Kukurydziana", IngrType.WRAP),
                new Ingredient("GRBF", "mielona wołowina ", IngrType.PROTEIN),
                new Ingredient("CARN", "kawałki mięsa", IngrType.PROTEIN),
                new Ingredient("TMTO", "pomidory pokrojone w kostkę", IngrType.VEGGIES),
                new Ingredient("LETC", "sałata", IngrType.VEGGIES),
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
        return "create_own";
    }

    @PostMapping
    public String proccesTaco(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model){
        System.out.println(design);
        if (errors.hasErrors())
        {
            errors.getAllErrors().forEach(e-> System.out.println(e.getDefaultMessage()));
            return "create_own";
        }
        log.info("Taco procces now...");
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngrType type){
        return ingredients.stream().filter(x-> x.getType().equals(type)).collect(Collectors.toList());
    }
}
