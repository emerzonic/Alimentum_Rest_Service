package alimentum.alimentum.service;

import alimentum.alimentum.entity.Recipe;
import alimentum.alimentum.entity.User;
import alimentum.alimentum.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
  public Boolean saveRecipe(Recipe recipe) {
    boolean recipeAdded = false;
    User user = userService.getUser();
    if(user.addRecipe(recipe)){
      recipe.setUsername(user.getUsername());
      recipeRepository.save(recipe);
      recipeAdded = true;
    }
    return recipeAdded;
  }


  @Override
  @Transactional
  public void deleteRecipe(Recipe recipe) {
    User user = userService.getUser();
    user.removeRecipe(recipe);
    recipeRepository.deleteById(recipe.getId());

  }
}



