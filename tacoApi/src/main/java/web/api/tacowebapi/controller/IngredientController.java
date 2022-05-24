package web.api.tacowebapi.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.api.tacowebapi.model.Ingredient;
import web.api.tacowebapi.model.Order;
import web.api.tacowebapi.repository.IngredientRepository;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private final IngredientRepository ingredientRepo;

    public IngredientController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @GetMapping
    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepo.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient ingredientById(@PathVariable("id") String id) {
        Optional<Ingredient> optIngredient = ingredientRepo.findById(id);
        return optIngredient.orElse(null);
    }
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient postIngredient(@RequestBody Ingredient
                                             ingredient) {
        return ingredientRepo.save(ingredient);
    }
    @PutMapping("/{ingredientId}")
    public Ingredient putIngredient(@RequestBody Ingredient
                                       ingredient, @PathVariable("ingredientId") String ingredientId) {
        Ingredient ingredientToUpdate = ingredientRepo.findById(ingredientId).orElse(null);
        return ingredientRepo.save(ingredient);
    }
    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId")
                                         String ingredientId) {
        try {
            ingredientRepo.deleteById(ingredientId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Ingredient not found");
        }
    }
}