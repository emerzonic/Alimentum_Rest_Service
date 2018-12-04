package alimentum.alimentum.service;

import alimentum.alimentum.entity.Recipe;

import java.util.List;

public interface APIService {
      List<Recipe> cleanUpSearchRecipes(String results);
}
