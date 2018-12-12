package alimentum.alimentum.service;

import alimentum.alimentum.entity.Recipe;

import java.util.List;


public interface RecipeService {
      List<Recipe> getRecipes(String username);
      Recipe getRecipe(Integer recipeId);
      Boolean saveRecipe(Recipe recipe, String username);
      void deleteRecipe(Recipe recipe, String username);

}
