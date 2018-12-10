package alimentum.alimentum.service;

import alimentum.alimentum.entity.Recipe;



public interface RecipeService {
      Boolean saveRecipe(Recipe recipe);
      void deleteRecipe(Recipe recipe);

}
