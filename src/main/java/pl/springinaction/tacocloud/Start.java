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

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("FIFE", "pszenna", Ingredient.IngrType.WRAP));
        ingredients.add(new Ingredient("BEAF", "Kukurydziana", Ingredient.IngrType.PROTEIN));
        ingredients.forEach(e -> ingredientRepository.save(e));
        Taco taco = new Taco();
        taco.setIngredients(ingredients);
        taco.setName("Name");
        tacoRepository.save(taco);

    }
}
