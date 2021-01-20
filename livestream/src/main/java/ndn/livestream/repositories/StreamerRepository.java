package ndn.livestream.repositories;

import ndn.livestream.entities.StreamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreamerRepository extends JpaRepository<StreamerEntity, Long> {
    Optional<StreamerEntity> findByStreamKey(String streamKey);
}
