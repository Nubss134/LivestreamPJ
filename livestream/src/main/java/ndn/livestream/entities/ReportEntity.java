package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity(name = "report")
public class ReportEntity extends BaseEntity {
    private Long videoId;
    private String reason;
    private String content;
    @Transient
    VideoEntity videoEntity;
}
