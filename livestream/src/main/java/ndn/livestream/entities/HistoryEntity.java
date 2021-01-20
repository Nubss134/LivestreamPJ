package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity(name="history")
public class HistoryEntity extends BaseEntity{
    Long memberId;
    Long videoId;
    @Transient
    String streamerName;
    @Transient
    String title;
    @Transient
    String avatar;
}
