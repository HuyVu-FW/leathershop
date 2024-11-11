package huyvu.leathershop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


//@Data khoong kế thừa từ BaseModel
@Data
@Entity
@Table(name = "leather")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Leather extends BaseModel {

    String name;
    Double price;
    String shortDescription;
    String description;
    String image1 = "";
    String image2 = "";
    String image3 = "";
    String image4 = "";

    //foreign key in table
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties
    Category category;


}
