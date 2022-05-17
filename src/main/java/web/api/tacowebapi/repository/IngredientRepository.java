package web.api.tacowebapi.repository;

import org.springframework.data.repository.CrudRepository;
import web.api.tacowebapi.model.Ingredient;

public interface IngredientRepository

        extends CrudRepository<Ingredient, String> {

}
