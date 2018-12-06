package alimentum.alimentum.service;

import alimentum.alimentum.util.Meal;
import alimentum.alimentum.util.RecipeUtilClass;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class APIServiceImpl implements APIService{

  @Override
  public List<Meal>getRecipes(String uri) {
    RestTemplate restTemplate = new RestTemplate();
    RecipeUtilClass result = restTemplate.getForObject(uri, RecipeUtilClass.class);
    return result.getMeals();
  }
}
