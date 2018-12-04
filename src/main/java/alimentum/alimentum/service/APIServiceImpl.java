package alimentum.alimentum.service;

import alimentum.alimentum.entity.Recipe;
import alimentum.alimentum.util.Meal;
import alimentum.alimentum.util.MealUtilClass;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIServiceImpl implements APIService{

  @Override
  public List<Recipe> cleanUpSearchRecipes(String results) {
    MealUtilClass mealUtilClass = new Gson().fromJson(results, MealUtilClass.class);
    List <Recipe> recipes = null;

    for (Meal m:mealUtilClass.getMeals()) {
      List <String> ingredients = null;
      List <String> measurements = null;

      Recipe recipe = new Recipe();

      recipe.setId(m.getIdMeal());
      recipe.setTitle(m.getStrMeal());
      recipe.setCategory(m.getStrCategory());
      recipe.setCountry(m.getStrArea());
      recipe.setInstructions(m.getStrInstructions());
      recipe.setImageUrl(m.getStrMealThumb());
      recipe.setVideoInstruction(m.getStrYoutube());


      String ing = m.getStrIngredient1();
      ingredients.add(ing);


    }

    System.out.println(results);
    return null;
  }
}
