package pl.springinaction.tacocloud;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.springinaction.tacocloud.model.Ingredient;
import pl.springinaction.tacocloud.model.Taco;
import pl.springinaction.tacocloud.model.User;
import pl.springinaction.tacocloud.repository.IngredientRepository;
import pl.springinaction.tacocloud.repository.TacoRepository;
import pl.springinaction.tacocloud.repository.UserRepository;
import pl.springinaction.tacocloud.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Start {


    public Start(UserService userService, TacoRepository tacoRepository, IngredientRepository ingredientRepository) {
//        String message = new SimpleGrantedAuthority("ROLE_USER").toString();
//        System.out.println(message);
        User user = new User();
        user.setUsername("username");
        user.setPassword("pass");
        user.setEnabled(true);
        user.setPhone("12312312");
        userService.addUSer(user);

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", Ingredient.IngrType.WRAP),
                new Ingredient("COCO", "Kukurydziana", Ingredient.IngrType.WRAP),
                new Ingredient("GRBF", "mielona wołowina ", Ingredient.IngrType.PROTEIN),
                new Ingredient("CARN", "kawałki mięsa", Ingredient.IngrType.PROTEIN),
                new Ingredient("TMTO", "pomidory pokrojone w kostkę", Ingredient.IngrType.VEGGIES),
                new Ingredient("LETC", "sałata", Ingredient.IngrType.VEGGIES),
                new Ingredient("CHED", "cheddar", Ingredient.IngrType.CHEESE),
                new Ingredient("JACK", "Monterey Jack", Ingredient.IngrType.CHEESE),
                new Ingredient("SLSA", "pikantny sos pomidorowy", Ingredient.IngrType.SAUCE),
                new Ingredient("SRCR", "śmietana", Ingredient.IngrType.SAUCE)

        );
        ingredients.forEach(e -> ingredientRepository.save(e));
        List<Ingredient> ingredientsForTaco = new ArrayList<>();
        ingredientsForTaco.add(ingredients.get(1));
        ingredientsForTaco.add(ingredients.get(2));
        ingredientsForTaco.add(ingredients.get(3));
        Taco taco = new Taco();
        taco.setIngredients(ingredientsForTaco);
        taco.setName("Name1");
        tacoRepository.save(taco);

    }
}
