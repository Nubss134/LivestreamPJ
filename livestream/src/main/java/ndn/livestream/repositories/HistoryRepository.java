package ndn.livestream.repositories;

import ndn.livestream.entities.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findByMemberId(Long id);
    Optional<HistoryEntity> findByMemberIdAndVideoId(Long memberId, Long videoId);
}
