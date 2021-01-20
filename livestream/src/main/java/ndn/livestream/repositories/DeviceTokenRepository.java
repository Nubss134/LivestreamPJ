package ndn.livestream.repositories;

import ndn.livestream.entities.DeviceTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceTokenEntity, Long> {
    List<DeviceTokenEntity> findByMemberIdIn(List<Long> memberIds);
}
