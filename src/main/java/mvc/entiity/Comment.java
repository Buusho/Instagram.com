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
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id",sequenceName = "comment_seq",allocationSize = 1)
public class Comment extends BaseEntity {

    private String comment;
    private LocalDate created_at;

@ManyToOne
    private Post post;

@OneToMany
    private List<Like> likes;

@ManyToOne
    private  User user;

}
