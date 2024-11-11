package huyvu.leathershop.model;

import huyvu.leathershop.model.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "TableUser")
@NoArgsConstructor
@AllArgsConstructor
//Các thuộc tính chung nếu không chỉ rõ
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User extends BaseModel {

    String userName;
    String password;
    String fullname;
    String email;
    String phone;
    String address;
    String gender;

    String birthday;
    String avatar;
    @ElementCollection
    Set<String> roles;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Cart cart;
}
