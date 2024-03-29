package pl.springinaction.tacocloud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.springinaction.tacocloud.model.Ingredient;

import javax.swing.text.html.Option;
import java.util.Optional;

@RepositoryRestResource(path = "tacos", collectionResourceRel = "tacos")
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

    Optional<Ingredient> getIngredientById(String id);
}
