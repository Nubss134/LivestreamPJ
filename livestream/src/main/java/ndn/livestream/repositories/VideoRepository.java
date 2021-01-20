package ndn.livestream.repositories;

import ndn.livestream.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
//    @Query(value = "select * from video v order by v.created_time limit 1", nativeQuery = true)
//    VideoEntity findLivestreamVideo(Long streamerId);
    List<VideoEntity> findByIdIn(List<Long> ids);
    List<VideoEntity> findByTitleContainsAndIsLiveTrue(String title);
    List<VideoEntity> findByCategoryIdAndIsLiveTrue(Long category);
    List<VideoEntity> findByIsLiveTrue();
    List<VideoEntity> findByStreamerIdAndIsLiveFalse(Long streamerId);
    Optional<VideoEntity> findByStreamerIdAndIsLiveTrue(Long streamerId);

    List<VideoEntity> findTop3ByIsLiveTrue();

    List<VideoEntity> findTop5ByCategoryIdAndIsLiveTrue(Long categoryId);


}
