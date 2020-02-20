package pl.springinaction.tacocloud.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String name;
    private final String id;
    private final IngrType type;
    public static enum IngrType{
        WRAP, PROTEIN, VEGIES, CHEESE, SAUCE
    }
}
