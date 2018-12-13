package alimentum.alimentum.controller;

import alimentum.alimentum.entity.Recipe;
import alimentum.alimentum.service.APIService;
import alimentum.alimentum.service.RecipeService;
import alimentum.alimentum.util.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RecipeController {
  private APIService apiService;
  private RecipeService recipeService;


  @Autowired
  public RecipeController(APIService apiService,RecipeService recipeService ){
    this.apiService = apiService;
    this.recipeService = recipeService;
  }

  @GetMapping("/searchByCategory/{category}")
  private List<Meal> searchByCategory(@PathVariable String category)throws Exception{
    final String uri = "https://www.themealdb.com/api/json/v1/1/filter.php?c=" + category;
    return apiService.getRecipes(uri);
  }

  @GetMapping("/searchByName/{name}")
  private List<Meal> searchByName(@PathVariable String name)throws Exception{
    final String uri = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + name;
    return apiService.getRecipes(uri);

  }

  @GetMapping("/searchByRecipeId/{recipeId}")
  private List<Meal> searchByRecipeId(@PathVariable String recipeId)throws Exception{
    final String uri = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + recipeId;
    return apiService.getRecipes(uri);

  }

  @GetMapping("/getUserRecipes/{username}")
  private List<Recipe> getRecipes(@PathVariable String username) throws Exception{
    List<Recipe> basicRecipes = new ArrayList<>();
    List<Recipe> recipes = recipeService.getRecipes(username);
    for(Recipe r: recipes){
      Recipe recipe = new Recipe();
      recipe.setIdMeal(r.getIdMeal());
      recipe.setId(r.getId());
      recipe.setStrMealThumb(r.getStrMealThumb());
      recipe.setStrMeal(r.getStrMeal());
      System.out.println(recipe.toString());
      basicRecipes.add(recipe);
    }
    return basicRecipes;
  }

  @PostMapping("/saveRecipe/{username}")
  private String saveRecipe(@RequestBody(required = false) Recipe recipe, @PathVariable String username){
    String message = "fail";
    if(recipeService.saveRecipe(recipe, username)){
      message = "success";
    }
    return message;
  }

}
