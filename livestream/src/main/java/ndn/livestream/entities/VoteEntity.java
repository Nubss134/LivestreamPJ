package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "vote")
public class VoteEntity {
    @Id
    private Long Id;
    private String title;
    private Long period;
    private String choice1;
    private String choice2;
    private String choice3;
}
