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
  private RecipeRepository recipeRepository;
  private UserService userService;

  @Autowired
  public RecipeServiceImpl(RecipeRepository recipeRepository, UserService userService) {
    this.recipeRepository = recipeRepository;
    this.userService = userService;
  }


  @Override
  @Transactional
  public List<Recipe> getRecipes(String username) {
    User user = userService.getUser(username);
    System.out.println(user.toString());
    return recipeRepository.getAllByUsernameLike(user.getUsername());
  }


  @Override
  @Transactional
  public Recipe getRecipe(Integer recipeId) {
    return recipeRepository.getById(recipeId);
  }


  @Override
  @Transactional
  public Boolean saveRecipe(Recipe recipe, String username) {
    boolean recipeAdded = false;
    User user = userService.getUser(username);
    if(user.addRecipe(recipe)){
      recipe.setUsername(user.getUsername());
      recipeRepository.save(recipe);
      recipeAdded = true;
    }
    return recipeAdded;
  }


  @Override
  @Transactional
  public void deleteRecipe(Recipe recipe, String username) {
    User user = userService.getUser(username);
    user.removeRecipe(recipe);
    recipeRepository.deleteById(recipe.getId());
  }
}



