package alimentum.alimentum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class RecipeController {

  @GetMapping("/search")
  private String  searchRecipes()
  {
    final String uri = "http://localhost:8080/springrestexample/employees.xml";
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    System.out.println(result);
    return result;
  }

}
