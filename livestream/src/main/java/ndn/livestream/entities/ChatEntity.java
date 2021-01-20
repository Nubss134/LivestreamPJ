package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

@Getter
@Setter
public class ChatEntity {
    private Long receiverId;
    private Long senderId;
    private String content;

}
