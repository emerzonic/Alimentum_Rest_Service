package alimentum.alimentum.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User {


//  @NotNull
//  @NotEmpty
  @Id
  @Column(name="username", unique = true)
  private String username;


  @Size(min = 4)
  @Column(name="password")
  private String password;


  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name="user_roles",joinColumns = {
          @JoinColumn(name="user_username", referencedColumnName = "username")},
          inverseJoinColumns = {
                  @JoinColumn(name="role_name", referencedColumnName = "name")})
  private List<Role> roles;


  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinColumn(name = "username")
  @OrderBy("id DESC")
  @MapKey(name = "idMeal")
  private Map<String, Recipe> recipes;

  public User() {}

  public User(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password) {
    this.username = username;
    this.password = password;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Map<String, Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(Map<String, Recipe> recipes) {
    this.recipes = recipes;
  }

  public Boolean addRecipe(Recipe recipe){
    boolean recipeAdded = false;
    if ( recipes == null) {
      recipes = new HashMap<>();
    }
    String key = recipe.getIdMeal();
    boolean hasRecipe = recipes.containsKey( key );
    if (!hasRecipe) {
      recipes.put(key, recipe);
      recipeAdded = true;
    }
    return recipeAdded;
  }


  public void removeRecipe(Recipe recipe){
    if(recipes == null){
      recipes = new HashMap<>();
    }
    recipes.remove(recipe.getIdMeal());
  }

  @Override
  public String toString() {
    return "User{" +
                   "username='" + username + '\'' +
                   ", password='" + password + '\'' +
                   '}';
  }
}
