package alimentum.alimentum.service;

import alimentum.alimentum.entity.Recipe;
import alimentum.alimentum.entity.User;
import alimentum.alimentum.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RecipeServiceImpl implements RecipeService {
  private final RecipeRepository recipeRepository;
  private final UserService userService;

  @Autowired
  public RecipeServiceImpl(RecipeRepository recipeRepository, UserService userService) {
    this.recipeRepository = recipeRepository;
    this.userService = userService;
  }


  @Override
  @Transactional
  public List<Recipe> getRecipes(Long userId) {
    User user = userService.getUser(userId);
    List<Recipe> recipes = recipeRepository.getRecipesByUserId(user.getId());
    return  recipes;
  }


  @Override
  @Transactional
  public Recipe getRecipe(Long recipeId) {
    return recipeRepository.getById(recipeId);
  }


  @Override
  @Transactional
  public Boolean saveRecipe(Recipe recipe, Long userId) {
    boolean recipeAdded = false;
    User user = userService.getUser(userId);
    if(user.addRecipe(recipe)){
      recipe.setUserId(user.getId());
      recipeRepository.save(recipe);
      recipeAdded = true;
    }
    return recipeAdded;
  }


  @Override
  @Transactional
  public void deleteRecipe(Long recipeId, Long userId) {
    recipeRepository.deleteById(recipeId);
  }
}



