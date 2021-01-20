package ndn.livestream.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity(name="device_token")
public class DeviceTokenEntity {
    @Id
    private Long memberId;
    private String deviceToken;
}
