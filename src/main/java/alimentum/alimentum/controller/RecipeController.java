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
  public RecipeController(APIService apiService,RecipeService recipeService ) {
    this.apiService = apiService;
    this.recipeService = recipeService;
  }

  @GetMapping("/searchByCategory/{category}")
  private List<Meal> searchByCategory(@PathVariable String category){
    final String uri = "https://www.themealdb.com/api/json/v1/1/filter.php?c=" + category;
    return apiService.getRecipes(uri);
  }

  @GetMapping("/searchByName/{name}")
  private List<Meal> searchByName(@PathVariable String name){
    final String uri = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + name;
    return apiService.getRecipes(uri);

  }

  @GetMapping("/searchByRecipeId/{recipeId}")
  private List<Meal> searchByRecipeId(@PathVariable String recipeId){
    final String uri = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + recipeId;
    return apiService.getRecipes(uri);

  }

  @GetMapping("/getUserRecipes/{username}")
  private List<Recipe> getRecipes(@PathVariable String username){
    List<Recipe> recipes = new ArrayList<>();
    for(int i = 1; i <= 12; i++) {
      Recipe recipe = new Recipe();
      recipe.setId(i);
      recipe.setIdMeal("12"+i);
      recipe.setStrMeal("Dry Rice");
      recipe.setStrMealThumb("http://exhibitionaffairs.com/wp-content/uploads/2016/11/Ex-Africa-5c-1.jpg");
      recipes.add(recipe);
    }
//  List<Recipe> recipes = recipeService.getRecipes(username);
    return recipes;
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
