package alimentum.alimentum.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RecipeController {

  @PostMapping("/search")
  private String  searchRecipes(@RequestParam(defaultValue = "") String searchTerm){
    final String uri = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + searchTerm;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    System.out.println(result);
    return result;
  }

}
