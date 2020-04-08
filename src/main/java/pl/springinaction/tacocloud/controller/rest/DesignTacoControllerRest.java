package pl.springinaction.tacocloud.controller.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.springinaction.tacocloud.model.Taco;
import pl.springinaction.tacocloud.repository.TacoRepository;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoControllerRest {

    private TacoRepository tacoRepository;

    public DesignTacoControllerRest(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public ResponseEntity<?> recentTacos(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("name").descending());
        return new ResponseEntity<>(tacoRepository.findAll(page).getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getOneTaco(@PathVariable(name = "id") Long id)
    {
        Optional<Taco> taco = tacoRepository.findById(id);
        if (taco.isPresent())
        {
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco addTacoToRepo(@RequestBody Taco taco)
    {
        return tacoRepository.save(taco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTaco( @RequestBody Taco taco, @PathVariable("id") Long id)
    {
        Optional<Taco> foundTaco =tacoRepository.findById(id);
        if (foundTaco.isPresent())
        {
            return new ResponseEntity<>(tacoRepository.save(taco), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> upadtePartial(@RequestBody Taco taco, @PathVariable("id") Long id)
    {
        Optional<Taco> foundTaco = tacoRepository.findById(id);
        System.out.println(taco.getIngredients());
        System.out.println(foundTaco.get().getIngredients());
        if (foundTaco.isPresent())
        {
            if (taco.getId() != null)
            {
                foundTaco.get().setId(taco.getId());
            }
            if (taco.getName() != null)
            {
                foundTaco.get().setName(taco.getName());
            }
            if (taco.getIngredients() != null)
            {
               taco.getIngredients().forEach(ingredient -> foundTaco.get().getIngredients().add(ingredient));
            }
            tacoRepository.save(foundTaco.get());
            return new ResponseEntity<>(taco, HttpStatus.CREATED);
        }else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaco(@PathVariable("id") Long id)
    {
        Optional<Taco> foundTaco = tacoRepository.findById(id);
        if (foundTaco.isPresent())
        {
            tacoRepository.delete(foundTaco.get());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
