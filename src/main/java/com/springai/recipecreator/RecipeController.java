package com.springai.recipecreator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("generate-recipe")
    public String generateRecipe(@RequestParam String ingredients, @RequestParam String cuisine, @RequestParam String dietaryRestrictions) {
        return recipeService.generateRecipe(ingredients, cuisine, dietaryRestrictions);
    }
    
}
