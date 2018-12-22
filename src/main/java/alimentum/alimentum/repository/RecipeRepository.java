package alimentum.alimentum.repository;

import alimentum.alimentum.entity.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  @EntityGraph(value = "recipe.details",type = EntityGraph.EntityGraphType.LOAD)
  Recipe getById(Long recipeId);

  List<Recipe> getRecipesByUserId(Long userId);


}
