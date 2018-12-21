package alimentum.alimentum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Username must not be empty!")
  @Column(name="username", unique = true)
  private String username;


  @Size(min = 4, message = "Password must be at least four(4) character!")
  @Column(name="password")
  private String password;

  @Transient
  private String confirmPassword;


  @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name="user_roles",joinColumns = {
          @JoinColumn(name="user_username", referencedColumnName = "username")},
          inverseJoinColumns = {
                  @JoinColumn(name="role_name", referencedColumnName = "name")})
  private List<Role> roles;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinColumn(name = "username")
  @OrderBy("id DESC")
  @MapKey(name = "idMeal")
  private Map<String, Recipe> recipes;

  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
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

  public void addRole(Role newRole) {
    if (roles == null) {
      roles = new ArrayList<>();
    }
    roles.add(newRole);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "User{" +
                   "username='" + username + '\'' +
                   ", password='" + password + '\'' +
                   ", confirmPassword='" + confirmPassword + '\'' +
                   ", roles=" + roles +
                   '}';
  }
}
