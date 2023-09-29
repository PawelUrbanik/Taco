package pl.springinaction.tacocloud.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient extends RepresentationModel<Ingredient> {
    @Id  private final String id;
    private final String name;
    private final IngrType type;

    public static enum IngrType {    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE  } }

