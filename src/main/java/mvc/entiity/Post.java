package mvc.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.BaseEntity.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id",sequenceName = "post_seq",allocationSize = 1)
public class Post extends BaseEntity {
    private String title;
    private String description;
    private LocalDate created_at;

@ManyToOne
    private User user;
@OneToMany
private List<Comment> comments;

@OneToMany
private List<Like>likes;

@OneToMany
    private List<Image> images;


    public void addUser(User user) {
    }
}
