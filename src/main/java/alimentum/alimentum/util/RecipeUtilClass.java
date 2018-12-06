package alimentum.alimentum.util;

import java.util.List;


public class RecipeUtilClass {
  private List<Meal> meals;

  public List<Meal> getMeals() {
    return meals;
  }

  public void setMeals(List<Meal> meals) {
    this.meals = meals;
  }

  @Override
  public String toString() {
    return "RecipeUtilClass{" +
                   "meals=" + meals +
                   '}';
  }
}
