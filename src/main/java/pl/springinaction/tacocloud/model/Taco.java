package pl.springinaction.tacocloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RestResource(path = "tacos")
public class Taco extends RepresentationModel<Taco> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    //TODO message from properties file
    @NotNull
    @Size(min = 5, message = "Nazwa musi posiadać przynajmniej 5 znaków")
    private String name;
//    @Size(min = 1, message = "Musisz wybrać przynajmniej jeden składnik")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;
}
