package pl.springinaction.tacocloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //TODO message from properties file
//    @NotNull
//    @Size(min = 5, message = "Nazwa musi posiadać przynajmniej 5 znaków")
    private String name;
//    @NotNull
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;
}
