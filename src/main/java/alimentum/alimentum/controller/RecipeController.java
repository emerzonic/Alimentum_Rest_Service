package alimentum.alimentum.controller;

import alimentum.alimentum.entity.Recipe;
import alimentum.alimentum.service.APIService;
import alimentum.alimentum.service.MapValidationErrorService;
import alimentum.alimentum.service.RecipeService;
import alimentum.alimentum.util.Meal;
import alimentum.alimentum.util.Message;
import alimentum.alimentum.util.RecipeSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RecipeController {

  @Value("${spring.sendgrid.api-key}")
  private String apiKey;
  private final APIService apiService;
  private final RecipeService recipeService;
  private MapValidationErrorService mapValidationErrorService;



  @Autowired
  public RecipeController(APIService apiService,RecipeService recipeService,
                          MapValidationErrorService mapValidationErrorService){
    this.apiService = apiService;
    this.recipeService = recipeService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @GetMapping("/api/recipes/searchByCategory/{category}")
  private List<Meal> searchByCategory(@PathVariable String category)throws Exception{
    final String uri = "https://www.themealdb.com/api/json/v1/"+ apiKey +
                               "/filter.php?c=" + category;
    return apiService.getRecipes(uri);
  }


  @PostMapping("/api/recipes/searchByName")
  private ResponseEntity<?> searchByName(@Valid @RequestBody RecipeSearch search,
                                         BindingResult result)throws Exception{
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
    if(errorMap != null)return errorMap;

    final String uri = "https://www.themealdb.com/api/json/v1/"+ apiKey +
                               "/search.php?s=" + search.getSearchTerm();
    List<Meal> recipes = apiService.getRecipes(uri);
    return new ResponseEntity<List>(recipes, HttpStatus.OK);
  }



  @GetMapping("/api/recipes/searchByRecipeId/{recipeId}")
  private List<Meal> searchByRecipeId(@PathVariable String recipeId) throws Exception{
    final String uri = "https://www.themealdb.com/api/json/v1/"+ apiKey +
                               "/lookup.php?i=" + recipeId;
    return apiService.getRecipes(uri);

  }


  @GetMapping("/api/currentUser/getUserRecipes/{userId}")
  private List<Recipe> getRecipes(@PathVariable Long userId) throws Exception{
    List<Recipe> basicRecipes = new ArrayList<>();
    for(Recipe r: recipeService.getRecipes(userId)){
      Recipe recipe = new Recipe();
      recipe.setId(r.getId());
      recipe.setIdMeal(r.getIdMeal());
      recipe.setStrArea(r.getStrArea());
      recipe.setStrMeal(r.getStrMeal());
      recipe.setUserId(r.getUserId());
      recipe.setStrCategory(r.getStrCategory());
      recipe.setStrMealThumb(r.getStrMealThumb());
      basicRecipes.add(recipe);
    }
    return basicRecipes;
  }


  @GetMapping("/api/currentUser/getOneRecipe/{recipeId}")
  private Recipe getOneRecipe(@PathVariable Long recipeId) throws Exception{
    return recipeService.getRecipe(recipeId);
  }


  @PostMapping("/api/currentUser/saveRecipe/{userId}")
  private Message saveRecipe(@RequestBody Recipe recipe,
                             @PathVariable Long userId)throws Exception{
    if(recipeService.saveRecipe(recipe, userId)){
      return new Message("success", "Your recipe was successfully saved!");
    }
    return new Message("fail","You already have this recipe as a favorite!");
  }



  @DeleteMapping("/api/currentUser/deleteRecipe/{userId}/{recipeId}")
  private Message deleteRecipe(@PathVariable Long userId,
                              @PathVariable Long recipeId) throws Exception{
     recipeService.deleteRecipe(recipeId, userId);
    return new Message("success", "Recipe was successfully deleted.");
  }

}
