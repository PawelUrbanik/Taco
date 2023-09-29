package pl.springinaction.tacocloud.controller.rest;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.springinaction.tacocloud.model.Ingredient;
import pl.springinaction.tacocloud.repository.IngredientRepository;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController(value = "/ingredients")
public class IngredientRestController {

    private final IngredientRepository ingredientRepository;

    public IngredientRestController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping(value = "/{id}", produces = {"application/hal+json"})
    public HttpEntity<Ingredient> getOne(@PathVariable String id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.getIngredientById(id);
        if (ingredientOptional.isPresent()) {
            final Ingredient ingredient = ingredientOptional.get();
            Link selfLink = linkTo(methodOn(IngredientRestController.class).getOne(ingredient.getId())).withSelfRel();
            ingredient.add(selfLink);
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
