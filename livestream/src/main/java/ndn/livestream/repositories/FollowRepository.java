package ndn.livestream.repositories;
import ndn.livestream.entities.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long> {

    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    FollowEntity findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    long countByFolloweeId(Long memberId);
    long countByFollowerId(Long memberId);
    @Query(value = "select f1.followee_id from follow f1 inner join follow f2 on" +
            " f1.followee_id = f2.follower_id" +
            " where f1.follower_id = ?1", nativeQuery = true)
    List<Long> findFriends(Long memberId);

    List<FollowEntity> findByFolloweeId(Long followeeId);
    List<FollowEntity> findByFollowerId(Long followerId);

}