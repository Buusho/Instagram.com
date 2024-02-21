package mvc.entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mvc.BaseEntity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "followers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id",sequenceName = "follower_seq",allocationSize = 1)
public class Follower extends BaseEntity {

@ElementCollection
    private List<Long> subscribers;
@ElementCollection
    private List<Long> subscriptions;

@OneToOne
    private User user;


}
