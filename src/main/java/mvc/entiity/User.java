package mvc.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.BaseEntity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id",sequenceName = "user_seq",allocationSize = 1)
public class User extends BaseEntity {
@Column(unique = true)
private String user_name;
private String password;
@Column(unique = true)
private String email;
private String phone_number;


@OneToMany
private  List<Post> posts;

@OneToOne
    private Like like;

@OneToMany
    private List<Comment> comments;

@OneToMany
    private List<Image>images;

@OneToOne
    private Follower follower;

@OneToOne
    private UserInfo userInfo;

}
