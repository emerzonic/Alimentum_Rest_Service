package alimentum.alimentum.util;

import javax.validation.constraints.NotBlank;

public class RecipeSearch {

  @NotBlank(message = "Search term must not be empty!")
  private String searchTerm;

  public RecipeSearch() {
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public void setSearchTerm(String searchTerm) {
    this.searchTerm = searchTerm;
  }

  @Override
  public String toString() {
    return "RecipeSearch{" +
                   "searchTerm='" + searchTerm + '\'' +
                   '}';
  }
}
