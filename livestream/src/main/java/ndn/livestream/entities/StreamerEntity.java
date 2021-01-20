package ndn.livestream.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@Setter
@Entity(name="streamer")
public class StreamerEntity {
    @Id
    private Long id;
    private String streamKey;
    private String title;
    private Long categoryId;
    private Boolean isOnline;
    private String liveThumbnail;
    @Transient
    private String nickName;
    @Transient
    private String categoryName;
    @Transient
    private Long videoId;
}
