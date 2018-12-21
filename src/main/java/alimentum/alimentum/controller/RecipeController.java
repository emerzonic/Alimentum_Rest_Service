package alimentum.alimentum.controller;

import alimentum.alimentum.entity.Recipe;
import alimentum.alimentum.service.APIService;
import alimentum.alimentum.service.RecipeService;
import alimentum.alimentum.util.Meal;
import alimentum.alimentum.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RecipeController {
  private final APIService apiService;
  private final RecipeService recipeService;


  @Autowired
  public RecipeController(APIService apiService,RecipeService recipeService ){
    this.apiService = apiService;
    this.recipeService = recipeService;
  }

  @GetMapping("/api/searchByCategory/{category}")
  private List<Meal> searchByCategory(@PathVariable String category)throws Exception{
    final String uri = "https://www.themealdb.com/api/json/v1/1/filter.php?c=" + category;
    return apiService.getRecipes(uri);
  }

  @GetMapping("/api/searchByName/{name}")
  private List<Meal> searchByName(@PathVariable String name)throws Exception{
    final String uri = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + name;
    return apiService.getRecipes(uri);

  }

  @GetMapping("/api/searchByRecipeId/{recipeId}")
  private List<Meal> searchByRecipeId(@PathVariable String recipeId) throws Exception{
    System.out.println(recipeId);
    final String uri = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + recipeId;
    return apiService.getRecipes(uri);

  }

  @GetMapping("/currentUser/getUserRecipes/{username}")
  private List<Recipe> getRecipes(@PathVariable String username) throws Exception{
    List<Recipe> basicRecipes = new ArrayList<>();
    for(Recipe r: recipeService.getRecipes(username)){
      Recipe recipe = new Recipe();
      recipe.setId(r.getId());
      recipe.setIdMeal(r.getIdMeal());
      recipe.setStrArea(r.getStrArea());
      recipe.setStrMeal(r.getStrMeal());
      recipe.setUsername(r.getUsername());
      recipe.setStrCategory(r.getStrCategory());
      recipe.setStrMealThumb(r.getStrMealThumb());
      basicRecipes.add(recipe);
    }
    return basicRecipes;

//    return recipeService.getRecipes(username);
  }


  @GetMapping("/currentUser/getOneRecipe/{recipeId}")
  private Recipe getOneRecipe(@PathVariable Integer recipeId) throws Exception{
    return recipeService.getRecipe(recipeId);
  }


  @PostMapping("/currentUser/saveRecipe/{username}")
  private Message saveRecipe(@RequestBody Recipe recipe,
                             @PathVariable String username)throws Exception{
    if(recipeService.saveRecipe(recipe, username)){
      return new Message("success", "Your recipe was successfully save!");
    }
    return new Message("failed","You already have this recipe as a favorite!");
  }

  @DeleteMapping("/currentUser/deleteRecipe/{username}/{recipeId}")
  private String deleteRecipe(@PathVariable String username,
                              @PathVariable Integer recipeId) throws Exception{
     recipeService.deleteRecipe(recipeId, username);
     return "success";
  }

}
