package pl.springinaction.tacocloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springinaction.tacocloud.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
