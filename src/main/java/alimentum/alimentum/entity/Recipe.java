package alimentum.alimentum.entity;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="recipe")
public class Recipe {

  @Id
  @Column(name = "id")
  private String id;

   @Column(name = "title")
  private String title;

   @Column(name = "category")
  private String category;

   @Column(name = "country")
  private String country;

   @Column(name = "instructions")
  private String instructions;

  @ElementCollection
   @Column(name = "ingredients")
  private List<String> ingredients;

   @Column(name = "imageUrl")
  private String imageUrl;

  @ElementCollection
  @Column(name = "measurements")
  private List<String> measurements;

   @Column(name = "measurements")
  private String videoInstruction;

   @Column(name = "sourceUrl")
  private String sourceUrl;

  public Recipe() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<String> getMeasurements() {
    return measurements;
  }

  public void setMeasurements(List<String> measurements) {
    this.measurements = measurements;
  }

  public String getVideoInstruction() {
    return videoInstruction;
  }

  public void setVideoInstruction(String videoInstruction) {
    this.videoInstruction = videoInstruction;
  }

  public String getSourceUrl() {
    return sourceUrl;
  }

  public void setSourceUrl(String sourceUrl) {
    this.sourceUrl = sourceUrl;
  }

  @Override
  public String toString() {
    return "Recipe{" +
                   "id=" + id +
                   ", title='" + title + '\'' +
                   ", category='" + category + '\'' +
                   ", country='" + country + '\'' +
                   ", instructions='" + instructions + '\'' +
                   ", ingredients=" + ingredients +
                   ", imageUrl='" + imageUrl + '\'' +
                   ", measurements=" + measurements +
                   ", videoInstruction='" + videoInstruction + '\'' +
                   ", sourceUrl='" + sourceUrl + '\'' +
                   '}';
  }
}
