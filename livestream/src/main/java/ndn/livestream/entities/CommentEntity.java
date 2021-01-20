package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

@Getter
@Setter
public class CommentEntity {
    private String username;
    private String content;
    private Long memberId;
}
