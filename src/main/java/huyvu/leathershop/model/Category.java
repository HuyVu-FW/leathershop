package huyvu.leathershop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@JsonIgnoreType
@Data
@Entity
@Table(name = "category")
public class Category extends  BaseModel{
    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Leather> leathers;


}
