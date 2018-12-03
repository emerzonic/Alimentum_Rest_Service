package alimentum.alimentum.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table
public class Recipe {

  @Column(name = "id")
  private long id;
   @Column(name = "title")
  private String title;
   @Column(name = "category")
  private String category;
   @Column(name = "country")
  private String country;
   @Column(name = "instructions")
  private String instructions;
   @Column(name = "ingredients")
  private List<String> ingredients;
   @Column(name = "imageUrl")
  private String imageUrl;
   @Column(name = "measurements")
  private List<String> measurements;
   @Column(name = "measurements")
  private String videoInstruction;
   @Column(name = "sourceUrl")
  private String sourceUrl;



}
