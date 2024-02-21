package mvc.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.BaseEntity.BaseEntity;
@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id",sequenceName = "like_seq",allocationSize = 1)
public class Like extends BaseEntity {

    private boolean is_like;


    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Post post;

    @OneToOne
    private User user;


}
