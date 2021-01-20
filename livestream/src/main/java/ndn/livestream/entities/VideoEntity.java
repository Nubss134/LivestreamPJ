package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity(name = "video")
public class VideoEntity extends BaseEntity{
    private Long streamerId;
    private Long categoryId;
    private String title;
    private String videoUrl;
    private Boolean isLive;
    private String liveThumbnail;
    private Long viewNumber;
    @Transient
    private String nickName;
    @Transient
    private String categoryName;
    @Transient
    private String avatarUrl;
    @Transient
    private Boolean isOpenVote;
}
