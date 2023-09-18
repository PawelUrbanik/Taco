package pl.springinaction.tacocloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders_table")
public class Order{

    @Id
    @GeneratedValue
    private Long id;
    private Date placedAt;
    @NotBlank(message = "To pole jest obowiązkowe")
    @Size(min = 3)
    private String firstname;
    @NotBlank(message = "To pole jest obowiązkowe")
    @Size(min = 3)
    private String lastname;
    @NotBlank(message = "To pole jest obowiązkowe")
    @Size(min = 3)
    private String city;
    @CreditCardNumber(message = "Podano nieprawidłowy numer karty")
    private String ccNumber;
    @Digits(integer = 3, fraction =0, message = "Nieprawidłowy kod CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;
}
