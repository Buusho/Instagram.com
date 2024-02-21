package mvc.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.BaseEntity.BaseEntity;
@Entity
@Table(name = "userinfos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id",sequenceName = "userinfo_seq",allocationSize = 1)
public class UserInfo  extends BaseEntity {


private String full_name;
private String biography;
private String gender;
private String image;

@OneToOne
    private User user;

}
