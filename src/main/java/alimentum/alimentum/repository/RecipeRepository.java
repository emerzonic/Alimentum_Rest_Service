package alimentum.alimentum.repository;

import alimentum.alimentum.entity.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
  @EntityGraph(value = "recipe.details",type = EntityGraph.EntityGraphType.LOAD)
  Recipe getById(Integer recipeId);
  List<Recipe> getAllByUsernameLike(String username);
}
