package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name="sensitive_word")
public class SensitiveWordEntity  extends BaseEntity {
    private Long livestreamId;
    private String word;
}
