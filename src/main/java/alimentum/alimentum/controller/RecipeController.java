package alimentum.alimentum.controller;

import alimentum.alimentum.service.APIService;
import alimentum.alimentum.util.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class RecipeController {
  private APIService apiService;


  @Autowired
  public RecipeController(APIService apiService) {
    this.apiService = apiService;
  }


  @PostMapping("/search")
  private List<Meal> searchRecipes(@RequestParam(defaultValue = "") String searchTerm){
    final String uri = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + searchTerm;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    apiService.cleanUpSearchRecipes(result);
    return null;
  }

}
