package alimentum.alimentum.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@NamedEntityGraph(
        name = "recipe.details",
        includeAllAttributes = true)
@Data
@Entity
@Table(name="recipe")
public class Recipe{


  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", unique = true)
  private Integer id;

  @Column(name = "meal_id")
  private String idMeal;

  @Column(name = "username")
  private String username;

  @Column(name = "title")
  private String strMeal;

  @Column(name = "category")
  private String strCategory;

  @Column(name = "country")
  private String strArea;

  @Column(name = "note")
  private String note;


  @ElementCollection
  @CollectionTable(name="instructions", joinColumns=@JoinColumn(name="recipe_id"))
  @Column(name="instructions", length = 255)
  private Set<String> strInstructions;

  @ElementCollection
  @CollectionTable(name="ingredients", joinColumns=@JoinColumn(name="recipe_id"))
  @Column(name="ingredients", length = 255)
  private Set<String> strIngredients;

  @ElementCollection
  @CollectionTable(name="measurements", joinColumns=@JoinColumn(name="recipe_id"))
  @Column(name="measurements", length = 255)
  private Set<String> strMeasurements;

  @Column(name = "imageUrl")
  private String strMealThumb;

  @Column(name = "sourceUrl")
  private String strSource;

  @Column(name = "videoUrl")
  private String strYoutube;

  public Recipe() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIdMeal() {
    return idMeal;
  }

  public void setIdMeal(String idMeal) {
    this.idMeal = idMeal;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getStrMeal() {
    return strMeal;
  }

  public void setStrMeal(String strMeal) {
    this.strMeal = strMeal;
  }

  public String getStrCategory() {
    return strCategory;
  }

  public void setStrCategory(String strCategory) {
    this.strCategory = strCategory;
  }

  public String getStrArea() {
    return strArea;
  }

  public void setStrArea(String strArea) {
    this.strArea = strArea;
  }

  public Set<String> getStrInstructions() {
    return strInstructions;
  }

  public void setStrInstructions(Set<String> strInstructions) {
    this.strInstructions = strInstructions;
  }

  public Set<String> getStrIngredients() {
    return strIngredients;
  }

  public void setStrIngredients(Set<String> strIngredients) {
    this.strIngredients = strIngredients;
  }

  public Set<String> getStrMeasurements() {
    return strMeasurements;
  }

  public void setStrMeasurements(Set<String> strMeasurements) {
    this.strMeasurements = strMeasurements;
  }

  public String getStrMealThumb() {
    return strMealThumb;
  }

  public void setStrMealThumb(String strMealThumb) {
    this.strMealThumb = strMealThumb;
  }

  public String getStrSource() {
    return strSource;
  }

  public void setStrSource(String strSource) {
    this.strSource = strSource;
  }

  public String getStrYoutube() {
    return strYoutube;
  }

  public void setStrYoutube(String strYoutube) {
    this.strYoutube = strYoutube;
  }

  @Override
  public String toString() {
    return "Recipe{" +
                   "id=" + id +
                   ", idMeal='" + idMeal + '\'' +
                   ", username='" + username + '\'' +
                   ", strMeal='" + strMeal + '\'' +
                   ", strCategory='" + strCategory + '\'' +
                   ", strArea='" + strArea + '\'' +
                   ", strMealThumb='" + strMealThumb + '\'' +
                   ", strSource='" + strSource + '\'' +
                   ", strYoutube='" + strYoutube + '\'' +
                   '}';
  }
}
