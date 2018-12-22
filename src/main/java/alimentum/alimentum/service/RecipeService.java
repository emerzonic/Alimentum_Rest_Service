package alimentum.alimentum.service;

import alimentum.alimentum.entity.Recipe;

import java.util.List;


public interface RecipeService {
      List<Recipe> getRecipes(Long userId);
      Recipe getRecipe(Long recipeId);
      Boolean saveRecipe(Recipe recipe, Long userId);
      void deleteRecipe(Long recipeId, Long userId);

}
