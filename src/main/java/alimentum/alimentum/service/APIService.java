package alimentum.alimentum.service;

import alimentum.alimentum.util.Meal;

import java.util.List;

public interface APIService {
      List<Meal> getRecipes(String uri);
}
