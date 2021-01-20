package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "follow")
public class FollowEntity extends BaseEntity{
    private Long followerId;
    private Long followeeId;
}
