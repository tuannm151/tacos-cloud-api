package com.example.tacocloud.controller;

import com.example.tacocloud.model.Ingredient;
import com.example.tacocloud.model.Ingredient.Type;
import com.example.tacocloud.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    private RestTemplate rest = new RestTemplate();

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(Objects.requireNonNull(rest.getForObject("http://localhost:8080/ ingredients", Ingredient[].class)));
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {

            model.addAttribute(type.toString().toLowerCase(),

                    filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("taco", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient>
                                                  ingredients, Type type) {
        List<Ingredient> ingrList = new ArrayList<Ingredient>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals(type))

                ingrList.add(ingredient);
        }
        return ingrList;
    }

    @PostMapping
    public String processDesign(@RequestParam("ingredients") String ingredientIds, @RequestParam("name") String tacoName, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        List<Ingredient> ingredients = new ArrayList<>();
        for (String ingredientId : ingredientIds.split(",")) {
            Ingredient ingredient = rest.getForObject("http://localhost:8080/ingredients/" + ingredientId, Ingredient.class);
            ingredients.add(ingredient);
        }
        Taco taco = new Taco();
        taco.setName(tacoName);
        taco.setIngredients(ingredients);
        rest.postForObject("http://localhost:8080/design", taco, Taco.class);
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }
}