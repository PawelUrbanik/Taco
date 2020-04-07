package pl.springinaction.tacocloud.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.springinaction.tacocloud.model.Taco;
import pl.springinaction.tacocloud.repository.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoControllerRest {

    private TacoRepository tacoRepository;

    public DesignTacoControllerRest(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public Iterable<Taco>  recentTacos(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("name").descending());
        return tacoRepository.findAll(page).getContent();
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
}
