package alimentum.alimentum.util;

import java.util.List;


public class MealUtilClass {
  private List<Meal> meals;

  public List<Meal> getMeals() {
    return meals;
  }

  public void setMeals(List<Meal> meals) {
    this.meals = meals;
  }

  @Override
  public String toString() {
    return "MealUtilClass{" +
                   "meals=" + meals +
                   '}';
  }
}
